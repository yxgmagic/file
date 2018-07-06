package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.BOefullinfo;
import com.zhichao.beans.guns.Lscinfo;
import com.zhichao.beans.guns.QueryVehicleCaseBO;

/**
 * <p>
  * 车辆案件查询功能Mapper文件
 * </p>
 *
 * @author imyzt
 * @since 2018-02-01
 */
public interface BOefullinfoMapper extends BaseMapper<BOefullinfo> {

	/**
	 * 根据车牌号查询精检站过磅数据
	 * @param vehicleid
	 * @return
	 */
	List<QueryVehicleCaseBO> selLscOverloadByVehicleId(@Param("vehicleid") String vehicleid);
	
	/**
	 * 根据车牌号查询不停车预检站过磅信息
	 * @param vehicleid
	 * @return
	 */
	List<QueryVehicleCaseBO> selHspOverloadByVehicleId(@Param("vehicleid") String vehicleid);
	
	/**
	 * 车辆案件查询list页面及查询功能
	 * @param vehicleid 车牌号
	 * @param casetime 案件时间
	 * @param start 案件时间区间开始时间
	 * @param end  案件时间区间结束时间
	 * @param overlimited 超重情况
	 * @param min 超重情况最小值
	 * @param max 超重情况最大值
	 * @param processed 1年内超限次数
	 * @param notprocessed 违章未处理次数
	 * @param areacode 所属区域
	 * @return
	 */
	List<Map<String, Object>> selList(@Param("vehicleid") String vehicleid,
			@Param("casetime") String casetime, @Param("start") String start,@Param("end") String end,
			@Param("overlimited") String overlimited,@Param("min") Integer min,@Param("max") Integer max,
			@Param("processed") Integer processed,@Param("notprocessed") Integer notprocessed,
			@Param("areacode") String areacode);
	
	/**
	 * 根据违章表id查询违章表数据
	 * @param id  违章表id
	 * @return
	 */
	List<Map<String, Object>> selOefullById(@Param("id") Integer id);
	
	
	/**
	 * 获取几张车头的图片
	 * @param id
	 * @return
	 */
	List<Lscinfo> getVehicleImages(@Param("checkno") String checkno);
	
	
}