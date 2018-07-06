package com.zhichao.admin.warpper.lawEnforcement;

import java.util.Map;

import com.zhichao.service.core.util.DictUtil;
import com.zhichao.core.base.warpper.BaseControllerWarpper;

/**
 * 将返回到页面的时间日期格式转化成年月日 时分秒
 * @author fengshuonan
 *
 */
public class BlacklistWarpper extends BaseControllerWarpper{

	public BlacklistWarpper(Object obj) {
		super(obj);
	}
	
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		
		map.put("isBlackName", DictUtil.selectNameByEnameNum("yes_no", map.get("isblack").toString()));
		map.put("isPunitiveName", DictUtil.selectNameByEnameNum("yes_no", map.get("ispunitive").toString()));
		
	}


}
