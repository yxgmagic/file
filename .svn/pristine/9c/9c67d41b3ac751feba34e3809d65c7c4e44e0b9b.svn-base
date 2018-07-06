package com.zhichao.service.compreAnalysis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.BOefullinfo;
import com.zhichao.beans.guns.QueryVehicleCaseBO;

/**
 * <p>
 * 违章详细信息 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-01
 */
public interface IBOefullinfoService extends IService<BOefullinfo> {

	/**
	 * 根据车牌号查询精检站和不停车预检站所有过磅数据
	 * @param vehicleid
	 * @return
	 */
	List<QueryVehicleCaseBO> selOverloadByVehicleId(@Param("vehicleid") String vehicleid);

	/**
	 * 车辆案件查询list页面及查询功能
	 * @param vehicleid 车牌号
	 * @param casetime 案件时间
	 * @param overlimited 超重情况
	 * @param processed 1年内超限次数
	 * @param notprocessed 违章未处理次数
	 * @param areacode 所属区域
	 * @return
	 */
	List<Map<String, Object>> selList(String vehicleid, String casetime, String overlimited, Integer processed,Integer notprocessed, String areacode);
	
	/**
	 * 根据违章表id查询违章表数据
	 * @param id  违章表id
	 * @return
	 */
	Map<String, Object> selOefullById(Integer id);
	

	/**
	 * 获取几张车头的图片
	 * @param id
	 * @return
	 */
	List<String> getVehicleImages(String checkno);

	/**
	 * 查询车辆详情信息,根据车牌号
	 * @param vid
	 * @return
	 */
    BOefullinfo selVehicleDetailByVehicleId(String vid);
}
