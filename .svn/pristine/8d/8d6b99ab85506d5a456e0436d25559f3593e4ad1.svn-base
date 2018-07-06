package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.beans.guns.Corpinfo;

/**
 * <p>
  * 源头企业检测数据表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-19
 */
public interface CorpinfoMapper extends BaseMapper<Corpinfo> {

	List<Corpinfo> selList(@Param("page") Page<Corpinfo> page, @Param("areacode") String areacode,
						   @Param("vehicleid") String vehicleid, @Param("corptime") String corptime,
						   @Param("begin") String begin, @Param("end") String end);

	Corpinfo selCorpInfo(@Param("id") Integer id);

	Map<String, String> getVehicleImages(@Param("id") Integer id);
	
	List<Double> calculateVehicle(@Param("areacode") String areacode);
}