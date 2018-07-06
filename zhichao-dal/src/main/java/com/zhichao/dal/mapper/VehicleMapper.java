package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Vehicle;

/**
 * <p>
  * 源头企业货运车辆信息表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-02
 */
public interface VehicleMapper extends BaseMapper<Vehicle> {

	
	/**
	 * 根据条件查询企业源头车辆信息
	 * @param vehicleid 车牌号码
	 * @param owername 车主
	 * @param bizcertid 道路运输证号
	 * @param corpname 源头企业
	 * @param owertel 联系方式
	 * @return
	 */
	List<Vehicle> queryVehicleByCondition(@Param("vehicleid")String vehicleid, @Param("owername")String owername, @Param("bizcertid")String bizcertid, @Param("corpname")String corpname,
			@Param("owertel")String owertel);

	/**
	 * 源头企业车辆管理详情
	 * @return
	 */
	Vehicle selById(Integer vehicleId);
	
	/**
	 * 车牌号唯一性校验
	 */
	Integer vehicleidIsExist(Vehicle vehicle);
}