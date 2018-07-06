package com.zhichao.service.detecManage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.detecManage.ILscinfoService;
import com.zhichao.beans.guns.Lscinfo;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.LscinfoMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 精检站数据表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-17
 */
@Service(value="lscinfoServiceImpl")
public class LscinfoServiceImpl extends ServiceImpl<LscinfoMapper, Lscinfo> implements ILscinfoService {

	@Resource
	private LscinfoMapper lscinfoMapper;
	
	@Override
	public List<Lscinfo> selList(Page<Lscinfo> page, String depts, String stationid, String vehicleid, String fctime, String chekStatus) {
		
		//设置时间查询条件
		String begin = null, end = null;
		if(!ToolUtil.isEmpty(fctime)) {
			begin = fctime.trim() + " 00:00:00";
			end = fctime.trim() + " 23:59:59";
		}

		stationid = YUtil.isNullOrEmptyReturnString(stationid,true);
		vehicleid = YUtil.isNullOrEmptyReturnString(vehicleid,true);

		return lscinfoMapper.selList(page, depts, stationid, vehicleid,chekStatus, fctime, begin, end);
	}

	@Override
	public List<Double> statistics(String depts, String stationid) {
		List<Double> result = new ArrayList<Double>();
		if(!ToolUtil.isEmpty(stationid) || !ToolUtil.isEmpty(depts)) {
			if(!ToolUtil.isEmpty(stationid)) {
				result = lscinfoMapper.calcStation(stationid);
			} else if(!ToolUtil.isEmpty(depts)) {
				result = lscinfoMapper.calcArea(depts);
				int rate = (int) (result.get(1) / (result.get(0) * 100));
				result.add((double)rate);
			}
		}
		return result;
	}

	@Override
	public List<String> getVehicleImages(Integer id) {

		Map<String, Object> vehicleImages = lscinfoMapper.getVehicleImages(id);

		List<String> list = new ArrayList<>();
		//如果什么都没拿到,直接返回空list
		if(vehicleImages == null) {
			return list;
		}
		vehicleImages.forEach((K,V) -> {
			if(!"".equals(V)) {
				list.add(V.toString());
			}
		});
		return list;

	}

	@Override
	public boolean updateById(Lscinfo entity, String rtimeString) {
		// TODO Auto-generated method stub
		//entity.setRctime(YUtil.StringToDate(rtimeString));
		Date rctime = YUtil.StringToDate(rtimeString);
		if(rctime != null) {
			entity.setRctime(rctime);
		}
		return super.updateById(entity);
	}

	@Override
	public List<Map<String, Object>> selHistoryListByVehicleid(String vehicleid) {
		return lscinfoMapper.selHistoryListByVehicleid(vehicleid);
	}

	@Override
	public Map<String, Object> findLscAndFixInfo(Integer id) {
		return lscinfoMapper.findLscAndFixInfo(id);
	}

	public boolean insert(Lscinfo entity, String ftimeString) {
		Date fctime = YUtil.StringToDate(ftimeString);
		if(fctime != null) {
			entity.setFctime(fctime);
		}
		return super.insert(entity);
	}
	

}
