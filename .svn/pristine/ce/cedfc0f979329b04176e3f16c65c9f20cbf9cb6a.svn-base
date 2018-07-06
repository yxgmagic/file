package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.CargoHandling;

/**
 * <p>
  * 超限货物处理 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-25
 */
public interface CargoHandlingMapper extends BaseMapper<CargoHandling> {
		
	List<Map<String, Object>> selList(@Param("withholdno") String withholdno, 
			@Param("stationid") String stationid, 
			@Param("vehicleid") String vehicleid, 
			@Param("unloadtime") String unloadtime,
			@Param("begin") String begin, 
			@Param("end") String end);

	Map<String, Object> selListById(@Param("cargoHandlingId") Integer cargoHandlingId);
	
	Map<String, Object> selListByWithholdno(@Param("withholdno") String withholdno);
	
	Map<String, Object> getInfoByStationid(@Param("id") String id);
}