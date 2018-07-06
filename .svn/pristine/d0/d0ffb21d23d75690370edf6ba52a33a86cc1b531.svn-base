package com.zhichao.admin.warpper.platformConfig;

import java.util.Map;

import com.zhichao.core.base.warpper.BaseControllerWarpper;
import com.zhichao.service.core.util.DictUtil;

public class LawEnforceManWapper extends BaseControllerWarpper {

	public LawEnforceManWapper(Object obj) {
		super(obj);
		
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String lemPoliticsStatusValue =  map.get("lemPoliticsStatus").toString();
		String lemSexValue =  map.get("lemSex").toString();
		String lemEduBgValue =  map.get("lemEduBg").toString();
		String lemDutyValue =  map.get("lemDuty").toString();
		String lemGradeValue =  map.get("lemGrade").toString();
		
		map.put("lemPoliticsStatusName", DictUtil.selectNameByEnameNum("PolCode", lemPoliticsStatusValue));
		map.put("lemSexName", DictUtil.selectNameByEnameNum("sex", lemSexValue));
		map.put("lemEduBgName", DictUtil.selectNameByEnameNum("eduBg", lemEduBgValue));
		map.put("lemDutyName", DictUtil.selectNameByEnameNum("lemDuty", lemDutyValue));
		map.put("lemGradeName", DictUtil.selectNameByEnameNum("lemGrade", lemGradeValue));
	}

}
