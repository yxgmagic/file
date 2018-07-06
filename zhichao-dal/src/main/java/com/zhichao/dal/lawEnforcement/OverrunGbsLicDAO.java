package com.zhichao.dal.lawEnforcement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OverrunGbsLicDAO {
	
	List<Map<String, Object>> selectOverrunGbsLicList(@Param("licid") String licid,
			@Param("carid") String carid,
			@Param("sourcename") String sourcename,
			@Param("drivername") String drivername,
			@Param("driverphone") String driverphone,
			@Param("wayid") String wayid);
	
}
