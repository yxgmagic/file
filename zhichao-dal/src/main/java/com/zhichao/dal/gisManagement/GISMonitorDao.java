package com.zhichao.dal.gisManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhichao.beans.guns.Image;

/**
 * GIS地图监控Dao层
 * @author yice
 * @Date 2018年1月3日 下午1:38:53
 */
@Repository
public interface GISMonitorDao {



	/**
	 * v2-zhichao
	 */
	// v2查询固定治超站坐标
	List<Map<String, Object>> selFixedSite();
	// v2查询预检站坐标
	List<Map<String, Object>> selPreSite();
	// v2 查询源头企业检测站
	List<Map<String, Object>> selCrop();
	// v2 查询高速公路出入口
	List<Map<String, Object>> selHsway();
	// v2 查询移动单兵坐标
	List<Map<String, Object>> sellawMan();
	// v2 查询移动单兵执法车
	List<Map<String, Object>> sellawCar();

	// v2根据坐标查询固定站sitecode
	Map<String, Object> selLnglatFixed(@Param("longitude") String longitude,
									   @Param("latitude") String latitude);
	// v2根据坐标查询预检站sitecode
	Map<String, Object> selLnglatPre(@Param("longitude") String longitude,
									 @Param("latitude") String latitude);
	// v2根据坐标查询源头企业corpcode
	Map<String, Object> selLnglatCorp(@Param("longitude") String longitude,
									  @Param("latitude") String latitude);
	// v2根据坐标查询高速公路出入口
	Map<String, Object> selLnglatHsway(@Param("longitude") String longitude,
									   @Param("latitude") String latitude);
	// v2根据坐标查询移动单兵
	Map<String, Object> selLnglatLawMan(@Param("longitude") String longitude,
										@Param("latitude") String latitude);
	// v2根据坐标查询移动执法车
	Map<String, Object> selLnglatLawCar(@Param("longitude") String longitude,
										@Param("latitude") String latitude);


	// v2固定站总车流量
	Integer selFixedInfo(@Param("stationid") String stationid,@Param("fctime") String fctime, @Param("start") String start, @Param("end") String end);

	// v2预检站总车流量
	Integer selPreInfo(@Param("stationid") String stationid,@Param("hsptime") String hsptime, @Param("start") String start, @Param("end") String end);

	// v2源头企业总车流量
	Integer selCorpInfo(@Param("corpcode") String corpcode,@Param("corptime") String corptime, @Param("start") String start, @Param("end") String end);

	// v2精检站超限车辆总数
	Integer getFixedOverCar(@Param("stationid") String stationid,@Param("fctime") String fctime, @Param("start") String start, @Param("end") String end);

	// v2预检站超限车辆总数
	Integer getPreOverCar(@Param("stationid") String stationid,@Param("hsptime") String hsptime, @Param("start") String start, @Param("end") String end);

	// v2源头企业超限车辆总数
	Integer getCorpOverCar(@Param("corpcode") String corpcode,@Param("corptime") String corptime, @Param("start") String start, @Param("end") String end);



	// v2精检站已处理超限车辆总数
	Integer getFixedProcessed(@Param("stationid") String stationid,@Param("fctime") String fctime, @Param("start") String start, @Param("end") String end);

	// v2预检站已处理超限车辆总数
	Integer getPreProcessed(@Param("stationid") String stationid,@Param("hsptime") String hsptime, @Param("start") String start, @Param("end") String end);


	Image selImgFixed(@Param("sitecode") String sitecode);




	List<Map<String, Object>> selectGISList(@Param("id") String id,
											@Param("longitude") String longitude,
											@Param("latitude") String latitude,
											@Param("areacode") String areacode);

	List<Map<String, Object>> selectPresiteList(@Param("id") String id,
												@Param("longitude") String longitude,
												@Param("latitude") String latitude,
												@Param("areacode") String areacode);



	Integer getCarCount(@Param("stationid") String stationid);
	Integer getCarCounts(@Param("stationid") String stationid);

	Integer getOverCar(@Param("stationid") String stationid);
	Integer getOverCars(@Param("stationid") String stationid);

	Integer getProcessed(@Param("stationid") String stationid);
	Integer getProcesseds(@Param("stationid") String stationid);


	List<Map<String, Object>> selectSiteFix(@Param("sitename") String sitename);
	List<Map<String, Object>> selectSitePre(@Param("sitename") String sitename);
	List<Map<String, Object>> selectSiteCorp(@Param("corpname") String corpname);
	List<Map<String, Object>> selectSiteHsway(@Param("hswayname") String hswayname);
	List<Map<String, Object>> selectSiteLawMan(@Param("lem_name") String lem_name);
	List<Map<String, Object>> selectSiteLawCar(@Param("manager") String manager, @Param("vehicleid") String vehicleid);

	/**
	 * 根据用户ID查询用户拥有的权限
	 */
	List<Map<String, Object>> selectOverRun(@Param("roleid") Integer roleid);

	/**
	 * 查询不停车预检站超限信息
	 */
	List<Map<String, Object>> selectOverRunPreSite();

	/**
	 * 更新不停车预检站超限信息
	 */
	Integer updateOverRunPreSite(@Param("id") Integer id);

	/**
	 * 查询精检站超限信息
	 */
	List<Map<String, Object>> selectOverRunFixedSite();

	/**
	 * 查询精检站超限信息
	 */
	Integer updateOverRunFixedSite(@Param("id") Integer id);

//	/**
//	 * 插入数据到超限信息弹框提示表
//	 */
//	Integer insertOverRun(@Param("vehicleid") String vehicleid,
//			@Param("stationid") String stationid,
//			@Param("violationlevel") String violationlevel,
//			@Param("deteno") String deteno,
//			@Param("time") Date time,
//			@Param("sitename") String sitename);
//
//	/**
//	 * 获取超限信息
//	 */
//	List<Map<String, Object>> selectOverRunInfo();


	/**
	 * ---------------------------------------------------------------------------
	 * 2018/2/8
	 */

}
