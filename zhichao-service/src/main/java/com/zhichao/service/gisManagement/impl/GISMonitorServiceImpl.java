package com.zhichao.service.gisManagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.DataAcptServer;
import com.zhichao.service.gisManagement.IGISMonitorService;
import com.zhichao.beans.guns.GISMonitor;
import com.zhichao.beans.guns.Image;
import com.zhichao.common.util.YUtil;
import com.zhichao.dal.gisManagement.GISMonitorDao;
import com.zhichao.dal.mapper.GISMonitorMapper;
import com.zhichao.service.common.impl.BsImageEntityServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 111
 * @since 2018-01-02
 */
@Service
public class GISMonitorServiceImpl extends ServiceImpl<GISMonitorMapper, GISMonitor> implements IGISMonitorService {

	@Autowired
	private GISMonitorDao gisMonitorDao;

	@Autowired
	private GISMonitorMapper gisMonitorMapper;

	//FIXME
	@Autowired
	private BsImageEntityServiceImpl bsImageEntityService;

	@Override
	public List<Map<String, Object>> selectGISList(String id, String longitude, String latitude, String areacode) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> GISList = gisMonitorDao.selectGISList(id, longitude, latitude, areacode);

		for (Map<String, Object> gisMap : GISList) {
			String stationid = (String) gisMap.get("sitecode");
			Integer carCount = gisMonitorDao.getCarCount(stationid);
			Integer overCar = gisMonitorDao.getOverCar(stationid);
			Integer processed = gisMonitorDao.getProcessed(stationid);
			gisMap.put("carCount", carCount);
			gisMap.put("overCar", overCar);
			gisMap.put("processed", processed);
		}
		return GISList;
	}

	@Override
	public List<Map<String, Object>> selectPresiteList (String id, String longitude, String latitude, String areacode) {
		List<Map<String, Object>> resultLists = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> GISLists = gisMonitorDao.selectPresiteList(id, longitude, latitude, areacode);

		for (Map<String, Object> gisMaps : GISLists) {
			String stationid = (String) gisMaps.get("sitecode");
			Integer carCounts = gisMonitorDao.getCarCounts(stationid);
			Integer overCars = gisMonitorDao.getOverCars(stationid);
			Integer processeds = gisMonitorDao.getProcesseds(stationid);
			gisMaps.put("carCount", carCounts);
			gisMaps.put("overCar", overCars);
			gisMaps.put("processed", processeds);
		}
		return GISLists;
	}
	@Override
	public Object selOverRun(String checkno) {
//		System.out.println(checkno);
		if(checkno == null) {
			checkno = "";
		}
//		List<Map<String, Object>> list = new ArrayList<>();
//		Object obj = new Object();
//		while (true) {
//			List<Map<String,Object>> list0 = gisMonitorMapper.selOverRunInfo();
//			List<Map<String, Object>> list1 = gisMonitorMapper.selOverRunInfos();
//			list0.addAll(list1);
//			Collections.sort(list0, new Comparator<Map<String, Object>>() {
//				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//					return o1.get("time").toString().compareTo(o2.get("time").toString());
//				}
//			});
//			Collections.reverse(list0);
//
//			list.addAll(list0);
//
//			Map<String, Object> resultMap = list.size() > 0 && list.get(0) != null ? list.get(0) : null;
//
//			if (null != resultMap && resultMap.containsKey("checkno")){
//				String _checkno = String.valueOf(resultMap.get("checkno"));
//				if(!_checkno.equals(checkno)){
//					obj = resultMap;
//					break;
//				}
//			}
//
//			/*if(list.size() > 0){
//				Map<String, Object> _list = list.get(0);
//				if(!_list.containsKey("checkno")) {
//					break;
//				} else {
//					String _checkno = _list.get("checkno").toString();
//					if(!_checkno.equals(checkno)){
//						obj = _list;
//						break;
//					}
//				}
//			}*/
//			list.clear();
//		}
		//FIXME serviceImpl引用controller
		DataAcptServer.removeList();
//		System.out.println("DataAcptServer.prelist: \n\t"+DataAcptServer.prelist);

		return DataAcptServer.prelist.size()>0?!checkno.equals(((Map)DataAcptServer.prelist.get(0)).get("checkno"))?DataAcptServer.prelist.get(0):null:null;
	}


	/**
	 * select siteinfo
	 * @param longitude
	 * @param latitude
	 * @param sitetype
	 * @param time
	 * @return {
	 *     img: String,
	 *     imgext: String,
	 *     siteName: String,
	 *     manager: String,
	 *     siteCode: String,
	 *     carNumber: Number,
	 *     overCar: Number,
	 *     processed: Number,
	 *     siteType: Number
	 * }
	 */
	@Override
	public Object siteInfo(String longitude, String latitude, String sitetype, String time) {

		String start = null, end = null;
		time = YUtil.isNullOrEmptyReturnString(time, true);
		if (!YUtil.isNullOrEmpty(time, true)){
			String [] times = time.split("~");
			if (times.length == 2){
				start = times[0];
				end = times[1];
			}
		}
		// !!! 去除首尾空格
		String End = end.trim();
		String Start = start.trim();


		/* *
		 * sitetype: 1-固定治超站
		 * sitetype: 2-不停车预检站
		 * sitetype: 3-高速公路出入口
		 * sitetype: 4-移动执法车
		 * sitetype: 5-源头企业
		 * sitetype: 6-移动单兵
		 */
		if (sitetype.equals("1")){
			Map<String, Object> fixedinfo = gisMonitorDao.selLnglatFixed(longitude, latitude);
			String sitecode = String.valueOf(fixedinfo.get("sitecode"));
			sitecode = sitecode.trim();
			Integer selFixedInfo = gisMonitorDao.selFixedInfo(sitecode, time, Start, End);
			Integer getFixedOverCar = gisMonitorDao.getFixedOverCar(sitecode, time, Start, End);
			Integer getFixedProcessed = gisMonitorDao.getFixedProcessed(sitecode, time, Start, End);
			Image fixedImg = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(fixedinfo.get("id"))), "fixedsite");
			Map<String, Object> res = new HashMap<>();
			res.put("img", fixedImg == null ? "" : fixedImg.getImg());
			res.put("imgext", fixedImg == null ? "" : fixedImg.getImgext());
			res.put("siteName", String.valueOf(fixedinfo.get("sitename")));
			res.put("siteCode", sitecode);
			res.put("carNumber", selFixedInfo);
			res.put("overCar", getFixedOverCar);
			res.put("processed", getFixedProcessed);
			res.put("manager", String.valueOf(fixedinfo.get("manager")));
			res.put("siteType", "1");
			return res;
		} else if (sitetype.equals("2")) {
			Map<String, Object> preinfo = gisMonitorDao.selLnglatPre(longitude, latitude);
			String sitecode = String.valueOf(preinfo.get("sitecode"));
			sitecode = sitecode.trim();
			Integer selPreInfo = gisMonitorDao.selPreInfo(sitecode, time, Start, End);
			Integer getPreOverCar = gisMonitorDao.getPreOverCar(sitecode, time, Start, End);
			Integer getPreProcessed = gisMonitorDao.getPreProcessed(sitecode, time, Start, End);
			Image fixedImg = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(preinfo.get("id"))), "presite");
			Map<String, Object> res = new HashMap<>();
			res.put("img", fixedImg == null ? "" : fixedImg.getImg());
			res.put("imgext", fixedImg == null ? "" : fixedImg.getImgext());
			res.put("siteName", String.valueOf(preinfo.get("sitename")));
			res.put("siteCode", sitecode);
			res.put("carNumber", selPreInfo);
			res.put("overCar", getPreOverCar);
			res.put("processed", getPreProcessed);
			res.put("manager", String.valueOf(preinfo.get("manager")));
			res.put("siteType", "2");
			return res;
		} else if (sitetype.equals("5")) {
			Map<String, Object> corpinfo = gisMonitorDao.selLnglatCorp(longitude, latitude);
			String corpcode = String.valueOf(corpinfo.get("corpcode"));
			Integer selCorpInfo = gisMonitorDao.selCorpInfo(corpcode, time, Start, End);
			Integer selCorpOverCar = gisMonitorDao.getCorpOverCar(corpcode, time, Start, End);
			Image fixedImg = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(corpinfo.get("id"))), "corp");
			Map<String, Object> res = new HashMap<>();
			res.put("img", fixedImg == null ? "" : fixedImg.getImg());
			res.put("imgext", fixedImg == null ? "" : fixedImg.getImgext());
			res.put("siteName", String.valueOf(corpinfo.get("corpname")));
			res.put("siteCode", String.valueOf(corpinfo.get("corpcode")));
			res.put("carNumber", selCorpInfo);
			res.put("overCar", selCorpOverCar);
			res.put("processed", "");
			res.put("manager", String.valueOf(corpinfo.get("manager")));
			res.put("siteType", "5");

			return res;
		} else if (sitetype.equals("3")){
			Map<String, Object> hsway = gisMonitorDao.selLnglatHsway(longitude, latitude);
			Image img = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(hsway.get("id"))), "hsway");
			Map<String, Object> res = new HashMap<>();
			res.put("img", img == null ? "" : img.getImg());
			res.put("imgext", img == null ? "" : img.getImgext());
			res.put("siteName", String.valueOf(hsway.get("hswayname")));
			res.put("siteCode", "");
			res.put("carNumber", "");
			res.put("overCar", "");
			res.put("processed", "");
			res.put("manager", "");
			res.put("siteType", "3");
			return res;
		} else if (sitetype.equals("6")) {
			Map<String, Object> lawman = gisMonitorDao.selLnglatLawMan(longitude, latitude);
			Image img = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(lawman.get("id"))), "lawman");
			Map<String, Object> res = new HashMap<>();
			res.put("img", img == null ? "" : img.getImg());
			res.put("imgext", img == null ? "" : img.getImgext());
			res.put("siteName", String.valueOf(lawman.get("lem_name")));
			res.put("siteCode", String.valueOf(lawman.get("lem_num")));
			res.put("carNumber", "");
			res.put("overCar", "");
			res.put("processed", "");
			res.put("manager", "");
			res.put("siteType", "6");
			return res;
		} else if (sitetype.equals("4")) {
			Map<String, Object> lawcar = gisMonitorDao.selLnglatLawCar(longitude, latitude);
			Image img = bsImageEntityService.findImg(Integer.parseInt(String.valueOf(lawcar.get("id"))), "lawcar");
			Map<String, Object> res = new HashMap<>();
			res.put("img", img == null ? "" : img.getImg());
			res.put("imgext", img == null ? "" : img.getImgext());
			res.put("siteName", String.valueOf(lawcar.get("vehicleid")));
			res.put("siteCode", String.valueOf(lawcar.get("vehicleno")));
			res.put("carNumber", "");
			res.put("overCar", "");
			res.put("processed", "");
			res.put("manager", String.valueOf(lawcar.get("manager")));
			res.put("siteType", "4");
			return res;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> selGISSeach(String sitename) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> fixed = gisMonitorDao.selectSiteFix(sitename);
		List<Map<String, Object>> pre = gisMonitorDao.selectSitePre(sitename);
		List<Map<String, Object>> corp = gisMonitorDao.selectSiteCorp(sitename);
		List<Map<String, Object>> hsway = gisMonitorDao.selectSiteHsway(sitename);
		List<Map<String, Object>> lawman = gisMonitorDao.selectSiteLawMan(sitename);
		List<Map<String, Object>> lawcar = gisMonitorDao.selectSiteLawCar(sitename, sitename);
		if (fixed.size() > 0){
			for(Map<String, Object> _fixed : fixed){
				_fixed.put("sitetype", "1");
				list.add(_fixed);
			}
		}
		if (pre.size() > 0){
			for(Map<String, Object> _pre : pre){
				_pre.put("sitetype", "2");
				list.add(_pre);
			}
		}

		if (corp.size() > 0){
			for(Map<String, Object> _corp : corp){
				Map<String, Object> c = new HashMap<>();
				c.put("sitename", _corp.get("corpname"));
				c.put("sitetype", "5");
				c.put("longitude", _corp.get("longitude"));
				c.put("latitude",  _corp.get("latitude"));
				list.add(c);
			}
		}

		if (hsway.size() > 0){
			for(Map<String, Object> _hsway : hsway){
				Map<String, Object> h = new HashMap<>();
				h.put("sitename", _hsway.get("hswayname"));
				h.put("sitetype", "3");
				h.put("longitude", _hsway.get("longitude"));
				h.put("latitude",  _hsway.get("latitude"));
				list.add(h);
			}
		}

		if (lawman.size() > 0){
			for(Map<String, Object> _lawman : lawman){
				Map<String, Object> lm = new HashMap<>();
				lm.put("sitename", _lawman.get("lem_name"));
				lm.put("sitetype", "6");
				lm.put("longitude", _lawman.get("longitude"));
				lm.put("latitude",  _lawman.get("latitude"));
				list.add(lm);
			}
		}

		if (lawcar.size() > 0){
			for(Map<String, Object> _lawcar : lawcar){
				Map<String, Object> lc = new HashMap<>();
				lc.put("sitename", _lawcar.get("manager"));
				lc.put("sitetype", "4");
				lc.put("longitude", _lawcar.get("longitude"));
				lc.put("latitude", _lawcar.get("latitude"));
				list.add(lc);
			}
		}
		return list;
	}
}














