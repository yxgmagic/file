package com.zhichao.service.detecManage;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Corpinfo;

/**
 * <p>
 * 源头企业检测数据表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-19
 */
public interface ICorpinfoService extends IService<Corpinfo> {
	
	/**
	 * 查询源头企业数据管理
	 * @param code
	 * @param vehicleid
	 * @param corptime
	 * @return
	 */
	List<Corpinfo> selList(Page<Corpinfo> page, String areacode, String vehicleid, String corptime);

	/**
	 * 跳转到修改源头企业数据管理
	 * @param hspid
	 * @return
	 */
	Corpinfo selCorpInfo(Integer id);

	/**
	 * 查看详情时异步获取图片
	 * @param id
	 * @return
	 */
	List<String> getVehicleImages(Integer id);
	
	/**
	 * 大屏获取数据
	 * @return
	 */
	List<Double> calculateVehicle(String areacode);

}
