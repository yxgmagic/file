package com.zhichao.dal.compreAnalysis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CarAreaCountDao {
//	Integer calcStation();
//
//	List<Map<String, Object>> selectListOverRun(@Param("fctime") String fctime,
//			@Param("violationlevel") Integer violationlevel,
//			@Param("start") String start,
//			@Param("end") String end);
//
////	List<Map<String, Object>> selectAreaOverRun(@Param("fctime") String fctime,
////			@Param("violationlevel") String violationlevel);
//
//	List<Map<String, Object>> selectAreaOverRun(@Param("fctime") String fctime,
//			@Param("violationlevel") Integer violationlevel,
//			@Param("start") String start,
//			@Param("end") String end);

	List<Map<String, Object>> selOverRunArea(@Param("fctime") String fctime,
											 @Param("start") String start,
											 @Param("end") String end,
											 @Param("level1") Integer level1,
											 @Param("level2") Integer level2,
											 @Param("stationid") String stationid);

	List<Map<String, Object>> selOverRunSite(@Param("level1") Integer level1,
											 @Param("level2") Integer level2,
                                             @Param("fctime") String fctime,
                                             @Param("start") String start,
                                             @Param("end") String end,
											 @Param("stationidArr") String[] stationidArr);
}
