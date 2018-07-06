package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.beans.guns.Hspinfo;

/**
 * <p>
  * 不停车预检站数据表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-15
 */
public interface HspinfoMapper extends BaseMapper<Hspinfo> {

	/**
	 * 获取list页面数据
	 * @param stationid
	 * @param vehicleid
	 * @param hsptime
	 * @param begin
	 * @param end
	 * @param depts
	 * @return
	 */
	List<Hspinfo> selList(@Param("page") Page<Hspinfo> page, @Param("depts") String[] depts, @Param("stationid") String stationid,
			@Param("vehicleid") String vehicleid, @Param("hsptime") String hsptime,@Param("begin") String begin,
			@Param("end") String end);

	/**
	 * 跳转到修改不停车预检数据管理时查询数据
	 * @param id
	 * @return
	 */
	Hspinfo selHspInfo(@Param("id") Integer id);

	/**
	 * 跳转到修改不停车预检数据管理时找图片
	 * @param id
	 * @return
	 */
	Map<String, String> getVehicleImages(@Param("id") Integer id);
	
	/**
	 * 计算大屏数据(根据站点)
	 * @param stationid
	 * @return
	 */
	List<Double> calcStation(@Param("stationid") String stationid);
		
	/**
	 * 计算大屏数据(根据部门)
	 * @param depts
	 * @return
	 */
	List<Double> calcDept(@Param("depts") String[] depts);

}