package com.zhichao.service.lawEnforcement.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.lawEnforcement.ICargoHandlingService;
import com.zhichao.beans.guns.CargoHandling;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.BlacklistMapper;
import com.zhichao.dal.mapper.CargoHandlingMapper;
import com.zhichao.dal.mapper.MakeCargoMapper;

/**
 * <p>
 * 超限货物处理 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-25
 */
@Service
public class CargoHandlingServiceImpl extends ServiceImpl<CargoHandlingMapper, CargoHandling> implements ICargoHandlingService {
	
	@Autowired
	CargoHandlingMapper cargoHandlingMapper;
	
	@Autowired
	MakeCargoMapper makeCargoMapper;
	
	@Autowired
	BlacklistMapper lacklistMapper;
	
	@Override
	public List<Map<String, Object>> selList(String withholdno, String stationid, String vehicleid,
			String unloadtime) {
		
		withholdno = YUtil.isNullOrEmptyReturnString(withholdno, true);
		vehicleid = YUtil.isNullOrEmptyReturnString(vehicleid, true);
		
		//设置时间查询条件
		String begin = null, end = null;
		if(!ToolUtil.isEmpty(unloadtime)) {
			begin = unloadtime.trim() + " 00:00:00";
			end = unloadtime.trim() + " 23:59:59";
		}
		
		List<Map<String, Object>> resultMap = cargoHandlingMapper.selList(withholdno, stationid, vehicleid, unloadtime, begin, end);
		
		
		return resultMap;
		
	}

	@Override
	public boolean insert(CargoHandling entity, String unloadtimeString) {
		if(!YUtil.isNullOrEmpty(unloadtimeString, true)) {
			Date unloadtime = YUtil.StringToDate(unloadtimeString);
			entity.setUnloadtime(unloadtime);
		}
		return super.insert(entity);
	}

	@Override
	public boolean updateById(CargoHandling entity, String unloadtimeString) {
		if(!YUtil.isNullOrEmpty(unloadtimeString, true)) {
			Date unloadtime = YUtil.StringToDate(unloadtimeString);
			entity.setUnloadtime(unloadtime);
		}
		
		return super.updateById(entity);
	}

	@Override
	public Map<String, Object> getPrintData(Integer cargoHandlingId) {
		Map<String, Object> cargoHandlingMap = this.selListById(cargoHandlingId);
		Map<String, Object> resultMap = new HashMap();
		resultMap.put("withholdno", cargoHandlingMap.get("withholdno"));
		resultMap.put("vehicleid", cargoHandlingMap.get("vehicleid"));
		resultMap.put("carowner", cargoHandlingMap.get("carowner"));
		resultMap.put("rtnumber", cargoHandlingMap.get("rtnumber"));
		resultMap.put("cpqnumber", cargoHandlingMap.get("cpqnumber"));
		resultMap.put("corpname", cargoHandlingMap.get("corpname"));
		
		//Unloading unloading = new Unloading();
		
		resultMap.put("unloadarea", cargoHandlingMap.get("ulloadname")); //
		resultMap.put("weightlimited", cargoHandlingMap.get("weightlimited"));
		resultMap.put("betotalweight", cargoHandlingMap.get("fctotalweight"));
		Integer betotalweight = (Integer) cargoHandlingMap.get("fctotalweight");
		Integer unloadweight = (Integer) cargoHandlingMap.get("unloadweight");
		Integer aftotalweight = betotalweight - unloadweight;
		resultMap.put("aftotalweight", aftotalweight); //
		Date unloadTime = (Date) cargoHandlingMap.get("unloadtime");
		String unloadtimeString = YUtil.DateToString(unloadTime, "yyyy年MM月dd日");
		resultMap.put("timeString", unloadtimeString); //
		
		return resultMap;
	}
	
	
	public Map<String, Object> selListById(Integer cargoHandlingId) {
		
		Map<String, Object> resultMap = cargoHandlingMapper.selListById(cargoHandlingId);
		Date unloadTime = (Date) resultMap.get("unloadtime");
		String unloadtimeString = YUtil.DateToString(unloadTime, "yyyy-MM-dd HH:mm:ss");
		resultMap.put("unloadtimeString", unloadtimeString);
		return resultMap;
	}

	@Override
	public Map<String, Object> selListByWithholdno(String withholdno) {

		return cargoHandlingMapper.selListByWithholdno(withholdno);
	}

	@Override
	public boolean deleteById(Serializable id) {
		
		//获取到即将删除的卸货信息
		CargoHandling cargoHandling = cargoHandlingMapper.selectById(id);
		
		//拿到卸货单号
		String withholdno = cargoHandling.getWithholdno();
		
		//获取到需要删除的接货单id
		List<Integer> ids = makeCargoMapper.getIdsByWithholdno(withholdno);
		
		//根据获取的接货单id删除接货单
		for(int i = 0; i < ids.size(); i++) {
			makeCargoMapper.deleteById(ids.get(i));
		}
		
		return super.deleteById(id);
	}

	@Override
	public Map<String, Object> getInfoByStationid(String id) {
		Map<String, Object> resultMap = cargoHandlingMapper.getInfoByStationid(id);
		if(!ToolUtil.isEmpty(resultMap)) {
			Integer weightlimited = (Integer)resultMap.get("weightlimited");	
			Integer fctotalweight = (Integer)resultMap.get("fctotalweight");
			Integer unloadweight = 0;
			if(ToolUtil.isEmpty(weightlimited) || ToolUtil.isEmpty(fctotalweight)) {
				unloadweight = 0;
			}else {
				unloadweight = fctotalweight - weightlimited;
				
			}
			List<String> corpNames;
			String corpName = "";
			String vehicleid = String.valueOf(resultMap.get("resultMap"));
			//根据车牌号获取企业名称的结果集 corpNames
			corpNames = lacklistMapper.getCorpNameByvehicleid(vehicleid);
			//如果获取到企业名称,则存到corpName
			if(corpNames.size() > 0) {
				corpName = corpNames.get(0);
			}
			resultMap.put("corpName", corpName);
			if(unloadweight < 0) {
				unloadweight = 0;
			}
			resultMap.put("unloadweight", unloadweight);
		} else {
			resultMap = new HashMap<String, Object>();
		}

		return resultMap;
	}
	
	
}
