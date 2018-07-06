package com.zhichao.service.siteRegistration;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Vehicle;

/**
 * <p>
 * 源头企业货运车辆信息表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
public interface IVehicleService extends IService<Vehicle> {

	/**
	 * 根据条件查询企业源头车辆信息
	 * @param vehicleid 车牌号码
	 * @param owername 车主
	 * @param bizcertid 道路运输证号
	 * @param corpname 源头企业
	 * @param owertel 联系方式
	 * @return
	 */
	List<Vehicle> queryVehicleByCondition(String vehicleid, String owername, String bizcertid, String corpname,
			String owertel);
	
	/**
	 * 源头企业车辆管理详情
	 * @return
	 */
	Vehicle selectById(Integer vehicleId);
	
	/**
	 * 车牌号唯一性校验
	 */
	Integer vehicleidIsExist(Integer id, String vehicleid);
}
