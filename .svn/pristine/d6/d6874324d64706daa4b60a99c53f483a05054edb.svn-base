package com.zhichao.admin.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.system.IIndexDataService;
import com.zhichao.core.base.controller.BaseController;

/**
 * 首页数据管理
 * @author fengshuonan
 *
 */
@Controller
@RequestMapping("/indexData")
public class IndexDataController extends BaseController {

	@Autowired
	IIndexDataService iIndexDataService;




	/**
	 * 获取首页统计信息以及通知信息
	 * @return
	 */
	@RequestMapping("/getIndexData")
	@ResponseBody
    public Map<String, Object> getStatisticsData(@RequestParam(value = "month") String month) {

        return iIndexDataService.getStatisticsData(month);
    }

	/**
	 * 根据条件获取指定时间区域内车流量信息
	 * @param dateArea
	 * @return
	 */
	@RequestMapping("/getTrafficFlow")
	@ResponseBody
    public Object getTrafficFlow(@RequestParam(value = "dataArea") String dateArea,
								 @RequestParam(value = "thatDeptid") String thatDeptid,
								 @RequestParam(value = "type") String type) {
		return iIndexDataService.getTrafficFlow(thatDeptid, dateArea, type);
	}

	/**
	 * 根据时间区间获取车轴统计信息
	 * @param dateArea
	 * @return
	 */
	@RequestMapping("/getTrafficAxle")
	@ResponseBody
	public Object getTrafficAxle(@RequestParam(value = "dataArea") String dateArea) {
		return iIndexDataService.getTrafficAxle(dateArea);
	}

}
