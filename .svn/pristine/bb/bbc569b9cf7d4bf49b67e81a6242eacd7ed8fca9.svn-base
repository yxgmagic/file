package com.zhichao.dal.siteRegistration;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 卸货场信息管理DAO
 * @author yice
 * @Date 2018年1月2日
 */
public interface UnloadingDAO {

	List<Map<String, Object>> selectUnloadingList(@Param("ulname") String drivername,
			@Param("address") String corpcode,
			@Param("manager") String idcard,
			@Param("managertel") String gender,
			@Param("sitecode") String driverid);
	
}
