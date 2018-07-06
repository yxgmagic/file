package com.zhichao.service.detecManage;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Meinfo;

/**
 * <p>
 * 流动执法车数据表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-22
 */
public interface IMeinfoService extends IService<Meinfo> {

	/**
	 * 根据条件查询数据记录
	 * @param depts 部门
	 * @param vehicleid 车牌号码
	 * @param fctime 检测时间
	 * @return List<Lscinfo>
	 */
	List<Meinfo> selList(Page<Meinfo> page,String depts,
			String vehicleid, String fctime);


	/**
	 * 通过部门获取统计信息
	 * @param depts 部门数组
	 * @return
	 */
	List<Double> statistics(String depts);
	

	/**
	 * 查看详情时异步获取图片
	 * @param id
	 * @return
	 */
	List<String> getVehicleImages(Integer id);

	/**
	 * 查询流动执法车详情
	 * @param meinfoId
	 * @return
	 */
	Meinfo selById(Integer meinfoId);
	
}
