package com.zhichao.service.lawEnforcement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.MakeCargo;

/**
 * <p>
 * 货物转运 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-30
 */
public interface IMakeCargoService extends IService<MakeCargo> {
	
	/**
	 * 修改版的条件查询
	 * @param withholdno
	 * @param stationid
	 * @param makevehicleid
	 * @param makecargodate
	 * @param makecargono
	 * @return
	 */
	List<Map<String, Integer>> selList(String withholdno, String stationid, 
			String makevehicleid, String makecargodate, String makecargono);
	
	/**
	 * 根据传进来的接货信息id进行查询 接货信息和卸货信息
	 * @param makeCargoId
	 * @return
	 */
	Map<String, Object> selListById(Integer makeCargoId);
	
	/**
	 * 获取打印的数据
	 * @param id
	 * @return
	 */
	Map<String, Object> getPrintData(Integer id);
}
