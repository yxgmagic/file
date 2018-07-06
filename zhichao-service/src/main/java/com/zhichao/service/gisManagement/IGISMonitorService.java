package com.zhichao.service.gisManagement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.GISMonitor;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
public interface IGISMonitorService extends IService<GISMonitor> {
	List<Map<String, Object>> selectGISList(String id,
											String longitude,
											String latitude,
											String areacode);

	List<Map<String, Object>> selectPresiteList(String id,
												String longitude,
												String latitude,
												String areacode);

//	List<Map<String, Object>> selectOverRun();

	Object selOverRun(String checkno);


	/**
	 * 查询站点详细信息
	 */
	Object siteInfo (String longitude, String latitude, String sitetype, String time);

	/**
	 * 查询站点
	 */
	List<Map<String, Object>> selGISSeach(String sitename);

}
