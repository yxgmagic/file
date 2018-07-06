package com.zhichao.service.lawEnforcement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Blacklist;

/**
 * <p>
 * 黑白名单 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-23
 */
public interface IBlacklistService extends IService<Blacklist> {
	
	/**
	 * 根据id获取车的图片
	 * @param vehicleid
	 * @return List<String> 里面是几个base64储存的图片
	 */
	List<String> getVehicleImages(String vehicleid);
	
	/**
	 * 根据车辆的车牌号,查找所有的超载记录(不停车监测站,固定治超站,流动执法车当中查找)
	 * @param vehicleid 车牌号
	 * @return List<Map<String, Object>> 所有的超载记录列表
	 */
	List<Map<String, Object>> getRecordList(String vehicleid);
	
	/**
	 * 根据条件查询相对应的列表
	 * @param vehicleid 车牌号
	 * @param drivername 驾驶人姓名
	 * @param corpname 公司名字
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selList(String vehicleid, String drivername, String corpname);
	
	/**
	 * 根据id修改是否为黑名单的状态
	 * @param id 需要更改的id
	 * @param statusValue 被更改的值
	 * @return void
	 */
	void updateBlackStatus(String id, String statusValue);
	
	/**
	 * 生成新的黑名单数据
	 * @return
	 */
	int generateBlacklist(String time);

	/**
	 * 修改之后的新增方法
	 * @param entity
	 * @param image0
	 * @param image1
	 * @param image2
	 * @return
	 */
	Object insert(Blacklist entity,
						  MultipartFile image0,
						  MultipartFile image1,
						  MultipartFile image2) throws IOException;
	
}
