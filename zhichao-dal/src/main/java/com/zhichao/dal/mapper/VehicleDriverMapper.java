package com.zhichao.dal.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.VehicleDriver;

/**
 * <p>
  * 源头企业货运人员信息表 Mapper 接口
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
public interface VehicleDriverMapper extends BaseMapper<VehicleDriver> {
	
	Integer dataIsExist(@Param(value = "id") Integer id, @Param(value = "idcard") String idcard, @Param(value = "driverid") String driverid, @Param(value = "qualificationid") String qualificationid);
}