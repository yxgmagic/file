package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.MakeCargo;

/**
 * <p>
  * 货物转运 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-30
 */
public interface MakeCargoMapper extends BaseMapper<MakeCargo> {
	
	List<Map<String, Integer>> selList(@Param("withholdno") String withholdno, 
			@Param("stationid") String stationid, 
			@Param("makevehicleid") String makevehicleid, 
			@Param("makecargodate") String makecargodate, 
			@Param("makecargono") String makecargono,
			@Param("begin") String begin,
			@Param("end") String end);
	Map<String, Object> selListById(@Param("makeCargoId") Integer makeCargoId);
	
	/**
	 * 根据卸货单号获取接货单的id
	 * @param withholdno
	 * @return
	 */
	List<Integer> getIdsByWithholdno(@Param("withholdno") String withholdno);
}