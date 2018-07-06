package com.zhichao.admin.warpper.platformConfig;

import java.util.List;
import java.util.Map;

import com.zhichao.service.core.util.DictUtil;
import com.zhichao.core.base.warpper.BaseControllerWarpper;

public class MunicipalWarpper extends BaseControllerWarpper {

	public MunicipalWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String value =  map.get("areatype").toString();
		map.put("areatypeName", DictUtil.selectNameByEnameNum("areatype", value));
	}

}
