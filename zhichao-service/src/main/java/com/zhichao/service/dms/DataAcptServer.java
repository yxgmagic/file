package com.zhichao.service.dms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.dms.DButil;
import com.zhichao.service.dms.impl.DBexcute;

/**
 * 数据服务器参数管理控制器
 *
 * @author fengshuonan
 * @Date 2018-02-26 15:46:04
 */
@Controller
@RequestMapping("/acptServer")
public class DataAcptServer extends BaseController {

	private String PREFIX = "/dms/acptServer/";
	public PubUtil pub = new PubUtil();
	public static List prelist = new ArrayList();
	public static Map stasionMap = new HashMap();
	public static Map<String, String> tabcol = new HashMap<String, String>();

	public static void removeList() {
		Iterator<Map> it = prelist.iterator();
		long heartbeat = Long.valueOf(ParaUtil.getParaValue("heartbeat"));
		while (it.hasNext()) {
			long longfctime = Long.valueOf((it.next()).get("heartbeat").toString());
			if (System.currentTimeMillis() - longfctime > heartbeat)
				it.remove();
		}
	}

	/**
	 * 数据接收
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add() {
		try {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			Enumeration<?> temp = req.getParameterNames();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			while (temp.hasMoreElements()) {
				String paramName = (String) temp.nextElement();
				String paramValue = req.getParameter(paramName);
				parameters.put(paramName, paramValue);
			}
			DButil db = new DBexcute();
			if (!stasionMap.containsKey(parameters.get("stationid"))) {
				List list = db.executeQueryList(
						"  select stationid ,sitetype,sitename , longitude ,latitude from bs_allsite_gis    ", null, 0);
				for (int k = 0; k < list.size(); k++) {
					String stationid = ((Map<String, Object>) list.get(k)).get("stationid").toString();
					stasionMap.put(stationid, (Map<String, Object>) list.get(k));
				}
			}
			if (tabcol.size() == 0) {
				tabcol.put("b_hspinfo", "hspid");
				tabcol.put("b_lscinfo", "checkno");
				tabcol.put("b_meinfo", "checkno");
				tabcol.put("b_corpinfo", "corpid");
				tabcol.put("b_oseinfo", "oseid");
			}
			if (parameters.containsKey("table_name")) {
				String col = "checkno";
				if (parameters.get("table_name").toString().equals("b_hspinfo")
						|| parameters.get("table_name").toString().equals("b_oseinfo"))
					col = "hspid";
				Map<String, Object> map = pub.getSequence(col, parameters.get("stationid").toString(),
						parameters.get("laneno").toString(), tabcol.get(parameters.get("table_name").toString()),
						parameters.get("table_name").toString());
				if (map.containsKey("ERROR")) {
					System.out.println(map);

				} else if (map.containsKey("sequence")) {
					parameters.put(tabcol.get(parameters.get("table_name").toString()), map.get("sequence").toString());
				}
				db.insertTableOne(parameters.get("table_name").toString(), parameters, 0);
				Map remap = new HashMap();
				remap.put("heartbeat", System.currentTimeMillis());
				remap.put("vehicleid", parameters.get("vehicleid"));
				remap.put("time", parameters.get("fctime"));
				if (map.containsKey("sequence")) {
					remap.put("checkno", map.get("sequence").toString());
				} else {
					remap.put("checkno", "");
				}
				if (stasionMap.containsKey(parameters.get("stationid"))) {
					remap.putAll((Map) stasionMap.get(parameters.get("stationid")));
					prelist.add(remap);
				}

				removeList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

}
