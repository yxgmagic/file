package com.zhichao.service.siteRegistration.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.beans.exception.BizExceptionEnum;
import com.zhichao.beans.exception.BussinessException;
import com.zhichao.beans.guns.Dept;
import com.zhichao.beans.guns.Fixedsite;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.DeptMapper;
import com.zhichao.dal.mapper.FixedsiteMapper;
import com.zhichao.service.common.constant.factory.ConstantFactory;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.siteRegistration.IFixedsiteService;
import com.zhichao.service.system.IDeptService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
@Service
public class FixedsiteServiceImpl extends ServiceImpl<FixedsiteMapper, Fixedsite> implements IFixedsiteService {


	@Resource
    DeptMapper deptMapper;
	
	@Resource
	FixedsiteMapper fixedsiteMapper;

	@Resource
	IDeptService deptService;

	/**
	 * 在进行添加固定治超站的时候,
	 * 需要在部门表下面创建一个部门,
	 * 用于权限管理
	 */
	@Override
	public boolean insert(Fixedsite entity) {
		//add 
		//新建一个部门对象用于插入到部门表
		Dept dept = new Dept();
		
		//对部门对象进行赋值,从传进来固定站获取值
		dept.setFullname(entity.getSitename());
		dept.setSimplename(entity.getSitename());
		dept.setPid(entity.getDeptpid());
		
		//设置 dept的 pids 属性
		deptSetPids(dept);
		
		//插入部门信息
		this.deptMapper.insert(dept);
		
		//在部门表中获取当前的部门
		Dept dept1 = this.deptMapper.selectOne(dept);
		
		//将获取到的部门的部门id 赋值给 治超站的 deptid 属性
		entity.setDeptid(dept1.getId());
		
		//返回治超站的数据插入情况
		return super.insert(entity);
	}

	@Override
	public boolean deleteById(Serializable id) {
		
		//获取到需要删除的治超站对象
		Fixedsite fixedsite = fixedsiteMapper.selectById(id);
		
		//拿到需要删除的部门id
		Integer deptid = fixedsite.getDeptid();


		//缓存被删除的部门名称
		LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptid));

		//删除部门id
		deptService.deleteDept(deptid);
		
		//返回删除治超站的情况
		return super.deleteById(id);
	}

	@Override
	public boolean updateById(Fixedsite entity) {
		
		//生成部门对象
		Dept dept = new Dept();
		
		//给部门对象赋值
		dept.setId(entity.getDeptid());
		dept.setPid(entity.getDeptpid());
		System.out.println(entity);
		System.out.println(dept);
		//根据刚刚生成的部门对象更新部门
		if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		deptSetPids(dept);
		deptMapper.updateById(dept);

		
		//返回删更新治超站的情况
		return super.updateById(entity);
	}

	public void deptSetPids(Dept dept) {
		if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
			dept.setPid(0);
			dept.setPids("[0],");
		} else {
			int pid = dept.getPid();
			Dept temp = deptMapper.selectById(pid);
			String pids = temp.getPids();
			dept.setPid(pid);
			dept.setPids(pids + "[" + pid + "],");
		}
	}
	
}
