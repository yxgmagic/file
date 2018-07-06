package com.zhichao.service.siteRegistration;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.VehicleDriver;

/**
 * <p>
 * 源头企业货运人员信息表 服务类
 * </p>
 *
 * @author zhichao
 * @since 2018-01-02
 */
public interface IVehicleDriverService extends IService<VehicleDriver> {
	Integer dataIsExist(Integer id, String idcard, String driverid, String qualificationid);
}
