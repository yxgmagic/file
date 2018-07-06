package com.zhichao.service.compreAnalysis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.compreAnalysis.IBOefullinfoService;
import com.zhichao.beans.guns.BOefullinfo;
import com.zhichao.beans.guns.Lscinfo;
import com.zhichao.beans.guns.QueryVehicleCaseBO;
import com.zhichao.dal.mapper.BOefullinfoMapper;

/**
 * <p>
 * 违章详细信息 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-01
 */
@Service
public class BOefullinfoServiceImpl extends ServiceImpl<BOefullinfoMapper, BOefullinfo> implements IBOefullinfoService {

	@Autowired
	private BOefullinfoMapper mapper;

	@Override
	public List<QueryVehicleCaseBO> selOverloadByVehicleId(String vehicleid) {

		vehicleid = YUtil.URLDecoderString(vehicleid);
		
		//诸个查询
		List<QueryVehicleCaseBO> lsc = mapper.selLscOverloadByVehicleId(vehicleid);
//		List<QueryVehicleCaseBO> hsp = mapper.selHspOverloadByVehicleId(vehicleid);
//
//		//合并数据
//		List<QueryVehicleCaseBO> allData = new ArrayList<QueryVehicleCaseBO>();
//
//		if (lsc != null && hsp.size() > 0) {
//			allData.addAll(lsc);
//		}
//		if (hsp != null && hsp.size() > 0) {
//			allData.addAll(hsp);
//		}
		
		//重组案件类型
		for (QueryVehicleCaseBO qb : lsc) {
			qb.setProstatus(DictUtil.selectNameByEnameNum("prostatus", qb.getProstatus()));
		}

		return lsc;
	}

	@Override
	public List<Map<String, Object>> selList(String vehicleid, String casetime,
			String overlimited, Integer processed, Integer notprocessed, String areacode) {

		//对数据进行非空判断，去前后空格处理
		vehicleid = YUtil.isNullOrEmptyReturnString(vehicleid, true);
		casetime = YUtil.isNullOrEmptyReturnString(casetime, true);
		overlimited = YUtil.isNullOrEmptyReturnString(overlimited, true);
		areacode = YUtil.isNullOrEmptyReturnString(areacode, true);

		//对时间进行处理
		String start = null, end = null;
		if (!YUtil.isNullOrEmpty(casetime, true)) {
			String[] time = casetime.split("~");
			if (time != null && time.length == 2) {
				start = time[0];
				end = time[1];
			}
		}

		//对超重情况进行处理
		Integer max = null, min = null;
		if (!YUtil.isNullOrEmpty(overlimited, true)) {
			String[] ol = overlimited.split("-");
			if (ol.length == 2) {

				//起
				min = Integer.parseInt(ol[0]);
				//止
				max = Integer.parseInt(ol[1]) == 0 ? null : Integer.parseInt(ol[1]); 
			}
		}

		return mapper.selList(vehicleid, casetime, start, end, overlimited, min, max, processed, notprocessed, areacode);
	}

	@Override
	public Map<String, Object> selOefullById(Integer id) {
		
		List<Map<String, Object>> oeList = mapper.selOefullById(id);
		
		//获取第一条记录
		Map<String, Object> oe = oeList.get(0);
		
		//查询案件状态
		String value = "未知！";
		if (oe.containsKey("prostatus")) {
			int status =  Integer.valueOf(String.valueOf(oe.get("prostatus")));
			value = status < 4 ? "未结案" : "已结案";
		}
		oe.put("punish", value);
		
		return oe;
	}

	@Override
	public List<String> getVehicleImages(String checkno) {

		List<Lscinfo> vehicleImagesList = mapper.getVehicleImages(checkno);
		
		List<String> list = new ArrayList<>();

		if (null != vehicleImagesList && vehicleImagesList.size() > 0){
			Lscinfo vehicle = vehicleImagesList.get(0);
			list.add(vehicle.getRcvehicleimage());
			list.add(vehicle.getRcvehicleimage1());
			list.add(vehicle.getRcvehicleimage2());

			return list;
		}

		return null;
	}

	@Override
	public BOefullinfo selVehicleDetailByVehicleId(String vid) {

		BOefullinfo oefullinfo;

		EntityWrapper<BOefullinfo> wrapper = new EntityWrapper<>();
		wrapper.where("vehicleid = {0}", vid);

		List<BOefullinfo> selectList = mapper.selectList(wrapper);

		//对查询结果进行非空判断
		if (selectList != null && selectList.size() > 0) {
			oefullinfo = selectList.get(0);
			//对时间进行改造
			if (null != oefullinfo){
				oefullinfo.setCaseTimeString(YUtil.DateToString(oefullinfo.getCasetime(),null));
			}
			return oefullinfo;
		}
		return null;
	}


}
