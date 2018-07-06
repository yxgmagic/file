package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.DeptAreasite;

/**
 * <p>
  * 用户部门区域站点权限表 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-25
 */
public interface DeptAreasiteMapper extends BaseMapper<DeptAreasite> {
	 List<Map<String, Object>> list(  @Param("areasitecode") String areasitecode, @Param("deptid") Integer deptid);
	 List<Map<String, Object>>getDeptAreaById( @Param("id") Integer id);
}