package com.zhichao.service.platformConfig.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.dal.mapper.AreaMapper;
import com.zhichao.service.platformConfig.IAreaService;
import com.zhichao.beans.guns.Area;

/**
 * <p>
 * 行政区域信息 服务实现类
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

	@Resource
	private AreaMapper mapper;
	
	@Override
	public List<Map<String, Object>> queryAreaByCondition(String address, String areatype, String areaname, String areacode,
			String pid) {
		address = address == null ? address : address.trim();
		areatype = (areatype == null) || ("0".equals(areatype)) ? null : areatype.trim();
		areaname = areaname == null ? areaname : areaname.trim();
		areacode = areacode == null ? areacode : areacode.trim();
		pid = pid == null ? pid : pid.trim();
		return mapper.queryAreaByCondition(address,areatype,areaname,areacode,pid);
	}

	@Override
	public List<Area> selByPid(Integer pid) {
		return mapper.selByPid(pid);
	}

	@Override
	public Area selById(Integer areaId) {
		return mapper.selById(areaId);
	}
	
}
