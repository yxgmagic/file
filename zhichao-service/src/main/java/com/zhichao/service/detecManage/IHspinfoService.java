package com.zhichao.service.detecManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Hspinfo;

/**
 * <p>
 * 不停车预检站数据表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-15
 */
public interface IHspinfoService extends IService<Hspinfo> {

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
	List<Hspinfo> selList(Page<Hspinfo> page, String depts, String stationid, String vehicleid, String hsptime);

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
	List<String> getVehicleImages(@Param("id") Integer id);
	
	/**
	 * 通过区域代码或者站点id获取统计信息
	 * @param areacode 区域代码
	 * @param stationid 站点id
	 * @return [vehicleNum, overloadNum, avgOverload, overloadPercentage]
	 */
	List<Double> statistics(String depts, String stationid);

}
