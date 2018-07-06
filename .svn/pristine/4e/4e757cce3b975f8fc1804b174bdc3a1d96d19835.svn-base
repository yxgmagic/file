package com.zhichao.service.platformConfig;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Area;

/**
 * <p>
 * 行政区域信息 服务类
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
public interface IAreaService extends IService<Area> {

	/**
	 * 查询行政区域列表
	 * @param address
	 * @param areatype
	 * @param areaname
	 * @param areacode
	 * @param arealetter
	 * @return
	 */
	List<Map<String, Object>> queryAreaByCondition(String address, String areatype, String areaname, String areacode,
			String arealetter);

	List<Area> selByPid(Integer pid);

	Area selById(Integer areaId);

	
}
