package com.zhichao.admin.warpper.siteRegistration;


import java.util.List;
import java.util.Map;

import com.zhichao.core.base.warpper.BaseControllerWarpper;
import com.zhichao.service.core.util.DictUtil;

public class VehicleDriverWarpper extends BaseControllerWarpper {

	public VehicleDriverWarpper(List<Map<String, Object>> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {

		map.put("gender", DictUtil.selectNameByEnameNum("sex", map.get("sex").toString()));
		
	}

}
