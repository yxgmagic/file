package com.zhichao.service.compreAnalysis.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhichao.common.util.YUtil;
import com.zhichao.dal.compreAnalysis.CarAreaCountDao;
import com.zhichao.service.compreAnalysis.ICarAreaCountService;

import net.sf.json.JSONArray;

@Service
public class CarAreaCountServiceImpl implements ICarAreaCountService{

	@Autowired
	private CarAreaCountDao carAreaCountDao;


//	@Override
//	public List<Map<String, Object>> selectListOverRun(String fctime, Integer violationlevel) {
//		String start = null, end = null;
//		fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
//
//		if (!YUtil.isNullOrEmpty(fctime, true)) {
//			String [] time = fctime.split("~");
//			if (time.length == 2) {
//				start = time[0];
//				end = time[1];
//			}
//		}
//
//		List<Map<String, Object>> CarList = carAreaCountDao.selectListOverRun(fctime, violationlevel, start, end);
//
//		for (Map<String, Object> carList: CarList) {
//			String vehicleids = (String) carList.get("SJC");
//			String shenId = vehicleids.substring(0, 1);
//			String shenName = this.setShenMing(shenId);
//			carList.put("shenName", shenName);
//			carList.remove("SJC");
//		}
//		return CarList;
//	}

//	@Override
//	public List<Map<String, Object>> selectAreaOverRun(String fctime, Integer violationlevel) {
//		String start = null, end = null;
//		fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
//		if (!YUtil.isNullOrEmpty(fctime, true)) {
//			String [] time = fctime.split("~");
//			if (time.length == 2) {
//				start = time[0];
//				end = time[1];
//			}
//		}
//		List<Map<String, Object>> AreaList = carAreaCountDao.selectAreaOverRun(fctime, violationlevel, start, end);
//		return AreaList;
//	}


    @Override
    public List<Map<String, Object>> selOverRunArea(String level, String fctime, String stationid) {
        String start = null, end = null;
        fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
        if (!YUtil.isNullOrEmpty(fctime, true)){
            String [] time = fctime.split("~");
            if (time.length == 2){
                start = time[0].trim();
                end = time[1].trim();
            }
        }
		int level1 = 0;
		int level2 = 0;
		if (level != "" && level != null) {
			String[] leve = level.split("-");
			if (leve.length == 2){
				level1 = Integer.parseInt(leve[0]);
				level2 = Integer.parseInt(leve[1]);
			}
		}


        List<Map<String, Object>> list = carAreaCountDao.selOverRunArea(fctime, start, end, level1, level2, stationid);
        for (Map<String, Object> carList: list) {
			String vehicleids = (String) carList.get("sjc");
			String shenName = this.setShenMing(vehicleids);
			carList.put("shenName", shenName);
			carList.remove("sjc");
		}
        return list;
    }

	@Override
	public List<Map<String, Object>> selOverRunSite(String level, String fctime, String[] stationids, String init) {
		if (init == "init") {
			return null;
		} else {
			String start = null, end = null;
			fctime = YUtil.isNullOrEmptyReturnString(fctime, true);
			if (!YUtil.isNullOrEmpty(fctime, true)){
				String [] time = fctime.split("~");
				if (time.length == 2){
					start = time[0].trim();
					end = time[1].trim();
				}
			}
			int level1 = 0, level2 = 0;
			if(!"".equals(level)){
				String[] leve = level.split("-");
				if (leve.length == 2){
					level1 = Integer.parseInt(leve[0]);
					level2 = Integer.parseInt(leve[1]);
				}
			}

			List<Map<String, Object>> list = carAreaCountDao.selOverRunSite(level1, level2, fctime, start, end, stationids);
			return list;

		}
	}

    /**
	 * 传入省份的简称返回全称
	 * @param name
	 * @return
	 */
    //FIXME 是否考虑换种解决方案，一对一关系是否可以放入缓存或者数据库，不建议在代码中直接写死
	public String setShenMing(String name) {
		String shenName = "";
		switch(name) {
			case "京":
				shenName = "北京市";
				break;
			case "津":
				shenName = "天津市";
				break;
			case "冀":
				shenName = "河北省";
				break;
			case "晋":
				shenName = "山西省";
				break;
			case "蒙":
				shenName = "内蒙古自治区";
				break;
			case "辽":
				shenName = "辽宁省";
				break;
			case "吉":
				shenName = "吉林省";
				break;
			case "黑":
				shenName = "黑龙江省";
				break;
			case "沪":
				shenName = "上海市";
				break;
			case "苏":
				shenName = "江苏省";
				break;
			case "浙":
				shenName = "浙江省";
				break;
			case "皖":
				shenName = "安徽省";
				break;
			case "闽":
				shenName = "福建省";
				break;
			case "赣":
				shenName = "江西省";
				break;
			case "鲁":
				shenName = "山东省";
				break;
			case "豫":
				shenName = "河南省";
				break;
			case "鄂":
				shenName = "湖北省";
				break;
			case "湘":
				shenName = "湖南省";
				break;
			case "粤":
				shenName = "广东省";
				break;
			case "桂":
				shenName = "广西壮族自治区";
				break;
			case "琼":
				shenName = "海南省";
				break;
			case "川":
				shenName = "四川省";
				break;
			case "贵":
				shenName = "贵州省";
				break;
			case "云":
				shenName = "云南省";
				break;
			case "渝":
				shenName = "重庆市";
				break;
			case "藏":
				shenName = "西藏自治区";
				break;
			case "陕":
				shenName = "陕西省";
				break;
			case "甘":
				shenName = "甘肃省";
				break;
			case "青":
				shenName = "青海省";
				break;
			case "宁":
				shenName = "宁夏回族自治区";
				break;
			case "新":
				shenName = "新疆维吾尔自治区";
				break;
			case "港":
				shenName = "香港特别行政区";
				break;
			case "澳":
				shenName = "澳门特别行政区";
				break;
			case "台":
				shenName = "台湾省";
				break;
			default:
				shenName = "不存在";
		}
		return shenName;
	}

}
