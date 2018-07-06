package com.zhichao.dal.employee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.employee.Emp;
/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author yanxingui
 * @since 2018-06-27
 */
public interface EmpMapper extends BaseMapper<Emp> {
	List<Map<String,Object>> selectListByEname(@Param("ename") String ename);
}
