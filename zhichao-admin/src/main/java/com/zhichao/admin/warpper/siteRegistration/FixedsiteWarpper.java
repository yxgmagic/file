package com.zhichao.admin.warpper.siteRegistration;


import java.util.List;
import java.util.Map;

import com.zhichao.core.base.warpper.BaseControllerWarpper;
import com.zhichao.service.core.util.DictUtil;

public class FixedsiteWarpper extends BaseControllerWarpper {

	public FixedsiteWarpper(List<Map<String, Object>> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		//字典没有，不用了
		
		
	}

}
