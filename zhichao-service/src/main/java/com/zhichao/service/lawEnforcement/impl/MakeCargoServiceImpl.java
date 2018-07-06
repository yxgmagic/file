package com.zhichao.service.lawEnforcement.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.lawEnforcement.IMakeCargoService;
import com.zhichao.beans.guns.MakeCargo;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.MakeCargoMapper;

/**
 * <p>
 * 货物转运 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-30
 */
@Service
public class MakeCargoServiceImpl extends ServiceImpl<MakeCargoMapper, MakeCargo> implements IMakeCargoService {

	@Autowired
	MakeCargoMapper makeCargoMapper;
	
	@Override
	public List<Map<String, Integer>> selList(String withholdno, String stationid, String makevehicleid,
			String makecargodate, String makecargono) {
		
		//设置时间查询条件
		String begin = null, end = null;
		if(!ToolUtil.isEmpty(makecargodate)) {
			begin = makecargodate.trim() + " 00:00:00";
			end = makecargodate.trim() + " 23:59:59";
		}
		
		
		return makeCargoMapper.selList(withholdno, stationid, makevehicleid, makecargodate, makecargono, begin, end);
	}

	@Override
	public Map<String, Object> selListById(Integer makeCargoId) {
		
		Map<String, Object> resultMap = makeCargoMapper.selListById(makeCargoId);
		if(!ToolUtil.isEmpty(resultMap)) {
			Date makecargodate = (Date) resultMap.get("makecargodate");
			String makecargodateString = YUtil.DateToString(makecargodate, "yyyy-MM-dd HH:mm:ss");
			resultMap.put("makecargodateString", makecargodateString);
		}
		
		return resultMap;
	}
	

	public Map<String, Object> getPrintData(Integer id) {
		
		Map<String, Object> makeCargoMap = this.selListById(id);
		
		Map<String, Object> resultMap = new HashMap();
		resultMap.put("makecargono",makeCargoMap.get("makecargono"));
		resultMap.put("makevehicleid",makeCargoMap.get("makevehicleid"));
		resultMap.put("makevehicleman",makeCargoMap.get("makevehicleman"));
		resultMap.put("unloadarea",makeCargoMap.get("ulloadname"));
		resultMap.put("unloadweight",makeCargoMap.get("unloadweight"));
		Date makecargodate = (Date) resultMap.get("makecargodate");
		String makecargodateString = YUtil.DateToString(makecargodate, "yyyy年MM月dd日");
		resultMap.put("makecargodateString",makecargodateString);
		return resultMap;
	}

	
}
