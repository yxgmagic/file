package com.zhichao.service.lawEnforcement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.CargoHandling;

/**
 * <p>
 * 超限货物处理 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-25
 */
public interface ICargoHandlingService extends IService<CargoHandling> {
	
	/**
	 * 超限货物处理查询列表
	 * @param withholdno
	 * @param ulloadcode
	 * @param vehicleid
	 * @param unloadtime
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selList(String withholdno, String stationid, String vehicleid, String unloadtime );
	
	/**
	 * 修改之后的添加方法,新增加了一个unloadtimeString字段,用来设置卸货日期
	 * @param entity
	 * @param unloadtimeString
	 * @return
	 */
	public boolean insert(CargoHandling entity, String unloadtimeString);
	
	/**
	 * 修改之后的更新方法,新增加了一个unloadtimeString字段,用来设置卸货日期
	 * @param entity
	 * @param unloadtimeString
	 * @return
	 */
	public boolean updateById(CargoHandling entity, String unloadtimeString);
	
	/**
	 * 获取打印需要的数据
	 * @param cargoHandlingId
	 * @return
	 */
	public Map<String, Object> getPrintData(Integer cargoHandlingId);
	
	/**
	 * 修改版的根据id查询数据,插入卸货场名称和精检站名称
	 * @param cargoHandlingId
	 * @return
	 */
	public Map<String, Object> selListById(Integer cargoHandlingId);
	
	/**
	 * 根据暂扣单号获取数据
	 * @param withholdno
	 * @return
	 */
	public Map<String, Object> selListByWithholdno(String withholdno);
	
	/**
	 * 根据根据站点数据id查询相关信息
	 * @param stationid
	 * @param vehicleid
	 * @return
	 */
	public Map<String, Object>getInfoByStationid(String id);
}
