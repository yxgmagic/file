package com.zhichao.admin.warpper.platformConfig;

import java.util.Map;

import com.zhichao.core.base.warpper.BaseControllerWarpper;
import com.zhichao.service.core.util.DictUtil;

public class PunishmentRulesWarpper extends BaseControllerWarpper{

	public PunishmentRulesWarpper(Object obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String trucksTypeValue =  map.get("trucksType").toString();
		String trucksAxlesValue =  map.get("trucksAxles").toString();
		
		map.put("trucksTypeName", DictUtil.selectNameByEnameNum("trucksType", trucksTypeValue));
		map.put("trucksAxlesName", DictUtil.selectNameByEnameNum("trucksAxles", trucksAxlesValue));
		
	}

}
