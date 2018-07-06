package com.zhichao.service.employee;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.employee.Emp;
/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author yanxingui123
 * @since 2018-06-26
 */
public interface EmpService extends IService<Emp> {
	List<Map<String,Object>> selectListByEname(String ename);
}
