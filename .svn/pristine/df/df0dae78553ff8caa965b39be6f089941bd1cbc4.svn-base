package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.beans.guns.Meinfo;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 流动执法车数据表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-22
 */
@Repository
public interface MeinfoMapper extends BaseMapper<Meinfo> {

	List<Meinfo> selList(@Param("page") Page<Meinfo> page,@Param("depts") String[] depts,@Param("vehicleid") String vehicleid,
			@Param("fctime") String fctime,@Param("begin") String begin,
			@Param("end") String end);

	/**
	 * 计算大屏数据(根据部门)
	 * @param depts
	 * @return
	 */
	List<Double> calcDept(@Param("depts") String[] depts);

	Map<String, String> getVehicleImages(Integer id);

	/**
	 * 查询详情
	 * @param meinfoId
	 * @return
	 */
	Meinfo selById(Integer meinfoId);
}