package com.zhichao.service.detecManage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.detecManage.IMeinfoService;
import com.zhichao.beans.guns.Meinfo;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.MeinfoMapper;

/**
 * <p>
 * 流动执法车数据表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-22
 */
@Service(value="meinfoServiceImpl")
public class MeinfoServiceImpl extends ServiceImpl<MeinfoMapper, Meinfo> implements IMeinfoService {

	@Autowired
	private MeinfoMapper mapper;

	@Override
	public List<Meinfo> selList(Page<Meinfo> page,String depts,
			String vehicleid, String fctime) {

		vehicleid = YUtil.isNullOrEmptyReturnString(vehicleid,true);

		//设置时间查询条件
		String begin = null, end = null;
		if(!ToolUtil.isEmpty(fctime)) {
			begin = fctime.trim() + " 00:00:00";
			end = fctime.trim() + " 23:59:59";
		}

		String[] deptsArr = null;
		if(depts != "" && depts != null) {
			deptsArr = depts.split(",");
		}
		return mapper.selList(page, deptsArr, vehicleid, fctime, begin, end);
	}

	@Override
	public List<Double> statistics(String depts) {
		List<Double> result = new ArrayList<Double>();
		if(!ToolUtil.isEmpty(depts)) {
			String[] deptsArr = YUtil.strToArr(depts, ",");
			result = mapper.calcDept(deptsArr);
			int rate = (int) (result.get(1) / (result.get(0) * 100));
			result.add((double)rate);
		}
		return result;
	}
	
	@Override
	public List<String> getVehicleImages(Integer id) {
		Map<String, String> vehicleImages = mapper.getVehicleImages(id);

		if (vehicleImages == null) {
			return null;
		}
		List<String> list = new ArrayList<>();
		vehicleImages.forEach((K,V) -> list.add(V));
		return list;
	}

	@Override
	public Meinfo selById(Integer meinfoId) {
		return mapper.selById(meinfoId);
	}

}
