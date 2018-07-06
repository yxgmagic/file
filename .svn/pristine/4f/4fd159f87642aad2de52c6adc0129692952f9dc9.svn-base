package com.zhichao.service.compreAnalysis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhichao.common.util.YUtil;
import com.zhichao.dal.compreAnalysis.SiteYoyAnalysisDao;
import com.zhichao.service.compreAnalysis.ISiteYoyAnalysisService;
import com.zhichao.dal.mapper.HspinfoMapper;

@Service
public class SiteYoyAnalysisServiceImpl implements ISiteYoyAnalysisService {

	@Autowired
	private SiteYoyAnalysisDao siteYoyAnalysisDao;

	@Autowired
    private HspinfoMapper hspinfoMapper;

	@Override
	public List<Object> selList(String casetime, String violationlevel) {
		String start = null, end = null;
		casetime = YUtil.isNullOrEmptyReturnString(casetime, true);
		if(!YUtil.isNullOrEmpty(casetime, true)){
			String [] time = casetime.split("~");
			if (time.length == 2){
				start = time[0];
				end = time[1];
			}
		}
        List<Object> list = new ArrayList<>();
		List<Map<String, Object>> listAxlesum = siteYoyAnalysisDao.selListAxlesum(casetime,start,end,violationlevel);

		list.add(listAxlesum);

		List<Map<String, Object>> listSite = siteYoyAnalysisDao.selListSite(casetime,start,end,violationlevel);
        list.add(listSite);

		return list;
	}

    @Override
    public List<Map<String, Object>> selListTable(String casetime, String violationlevel) {
        String start = null, end = null;
        casetime = YUtil.isNullOrEmptyReturnString(casetime, true);
        if(!YUtil.isNullOrEmpty(casetime, true)){
            String [] time = casetime.split("~");
            if (time.length == 2){
                start = time[0];
                end = time[1];
            }
        }
        List<Map<String, Object>> listTable = siteYoyAnalysisDao.selListTable(casetime, start, end,violationlevel);
        return listTable;
    }

	@Override
	public Object selectNumberSite(String period) {
		String start = null,
				end = null;
		period = YUtil.isNullOrEmptyReturnString(period, true);
		if(!YUtil.isNullOrEmpty(period, true)){
			String [] time = period.split("~");
			if(time.length == 2){
				start = time[0];
				end = time[1];
			}
		}
		// !!! 去除首尾空格
		String End = end.trim();
		String Start = start.trim();
        List<Map<String, Object>> presiteCarNumber = siteYoyAnalysisDao.selectNumberPreSite(period, Start, End);
        List<Map<String, Object>> fixedsiteCarNumber = siteYoyAnalysisDao.selectNumberFixedSite(period, Start, End);

		List<Map<String, Object>> list = new ArrayList<>();

		if ( presiteCarNumber.size() > 0 ){
            list.addAll(presiteCarNumber);
        }

        if ( fixedsiteCarNumber.size() > 0 ) {
            list.addAll(fixedsiteCarNumber);
        }
		return list;
	}

    @Override
    public Object selectOverRun(String fctime) {
        String start = null,
                end = null;
        fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
        if(!YUtil.isNullOrEmpty(fctime, true)){
            String [] time = fctime.split("~");
            if(time.length == 2){
                start = time[0];
                end = time[1];
            }
        }
        // !!! 去除首尾空格
        String End = end.trim();
        String Start = start.trim();
        List<Map<String, Object>> list = siteYoyAnalysisDao.selectOverRun(fctime,Start,End);
	    return list;
    }

    @Override
    public List<Map<String, Object>> selectCarGoType(String fctime) {
        String start = null,
                end = null;
        fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
        if(!YUtil.isNullOrEmpty(fctime, true)){
            String [] time = fctime.split("~");
            if(time.length == 2){
                start = time[0];
                end = time[1];
            }
        }
        // !!! 去除首尾空格
        String End = end.trim();
        String Start = start.trim();
        List<Map<String, Object>> list = siteYoyAnalysisDao.selectCargoType(fctime, Start, End);

        if (list.size() > 0){
            return list;
        } else {
            return null;
        }
    }
}
