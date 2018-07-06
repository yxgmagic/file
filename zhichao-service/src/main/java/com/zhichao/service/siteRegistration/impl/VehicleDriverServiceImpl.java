package com.zhichao.service.siteRegistration.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.siteRegistration.IVehicleDriverService;
import com.zhichao.beans.guns.VehicleDriver;
import com.zhichao.dal.mapper.VehicleDriverMapper;

/**
 * <p>
 * 源头企业货运人员信息表 服务实现类
 * </p>
 *
 * @author zhichao
 * @since 2018-01-02
 */
@Service
public class VehicleDriverServiceImpl extends ServiceImpl<VehicleDriverMapper, VehicleDriver> implements IVehicleDriverService {
	@Resource
	private VehicleDriverMapper mapper;
	
	@Override
	public Integer dataIsExist(Integer id, String idcard, String driverid, String qualificationid) {
		return mapper.dataIsExist(id, idcard, driverid, qualificationid);
	}
}
