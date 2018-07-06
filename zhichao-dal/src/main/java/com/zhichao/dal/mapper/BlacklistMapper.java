package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Blacklist;

/**
 * <p>
  * 黑白名单 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-23
 */
@Repository
public interface BlacklistMapper extends BaseMapper<Blacklist> {
	
	/**
	 * 通过vehicleid在数据库中拿车头照片
	 * @param vehicleid
	 * @return Map<String, Object> 用Map的键值对进行包装返回["vehicleimage","..."]...
	 */
	Blacklist getVehicleImages(@Param("vehicleid") String vehicleid);
	
	/**
	 * 根据条件到数据库查询相对应的数据
	 * @param vehicleid 车牌号
	 * @param drivername 驾驶人姓名
	 * @param corpname 公司名
	 * @return
	 */
	List<Map<String, Object>> selList(@Param("vehicleid") String vehicleid, 
			@Param("drivername") String drivername, 
			@Param("corpname") String corpname);
	
	/**
	 * 更新黑名单状态
	 * @param id 黑名单id
	 * @param statusValue 黑名单状态值
	 */
	void updateBlackStatus(@Param("id") String id, 
			@Param("statusValue") String statusValue);
	

	/**
	 * 生成黑白名单
	 * (在立案表中查询)
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Blacklist> generateBlacklist(@Param("time") String time, @Param("begin") String begin,@Param("end") String end);
	
	/**
	 * 根据车牌号获取企业名称
	 * @param vehicleid 车牌号
	 * @return
	 */
	List<String> getCorpNameByvehicleid(@Param("vehicleid") String vehicleid);
	
	/**
	 * 根据车牌号获取当前表里的记录条数
	 * @param vehicleid
	 * @return
	 */
	int getRowCount(@Param("vehicleid") String vehicleid);

}