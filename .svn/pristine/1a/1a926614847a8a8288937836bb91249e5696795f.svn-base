package com.zhichao.service.siteRegistration.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.siteRegistration.IVehicleService;
import com.zhichao.beans.guns.Vehicle;
import com.zhichao.dal.mapper.VehicleMapper;

/**
 * <p>
 * 源头企业货运车辆信息表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements IVehicleService {

	@Resource
	private VehicleMapper mapper;
	
	@Override
	public List<Vehicle> queryVehicleByCondition(String vehicleid, String owername, String bizcertid, String corpname,
			String owertel) {
		vehicleid = vehicleid == null ? vehicleid : vehicleid.trim();
		owername = owername == null ? owername : owername.trim();
		bizcertid = bizcertid == null ? bizcertid : bizcertid.trim();
		corpname = corpname == null ? corpname : corpname.trim();
		owertel = owertel == null ? owertel : owertel.trim();
		return mapper.queryVehicleByCondition(vehicleid,owername,bizcertid,corpname,owertel);
	}

	@Override
	public Vehicle selectById(Integer vehicleId) {
		return mapper.selById(vehicleId);
	}

	@Override
	public Integer vehicleidIsExist(Integer id, String vehicleid) {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(id);
		vehicle.setVehicleid(vehicleid);
		return mapper.vehicleidIsExist(vehicle);
	}

	
}
