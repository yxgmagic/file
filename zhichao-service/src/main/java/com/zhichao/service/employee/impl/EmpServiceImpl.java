package com.zhichao.service.employee.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.beans.employee.Emp;
import com.zhichao.dal.employee.EmpMapper;
import com.zhichao.service.employee.EmpService;
/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author yanxingui123
 * @since 2018-06-26
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
	@Resource
	private EmpMapper mapper ;
	
	
	public List<Map<String,Object>> selectListByEname(String ename) {
		return mapper.selectListByEname(ename) ;
	}
}
