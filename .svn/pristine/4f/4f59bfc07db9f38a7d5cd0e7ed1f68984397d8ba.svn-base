package com.zhichao.service.compreAnalysis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhichao.dal.compreAnalysis.CarVolumeCountDao;
import com.zhichao.service.compreAnalysis.ICarVolumeCountService;
import com.zhichao.common.util.ToolUtil;

@Service
public class CarVolumeCountServiceImpl implements ICarVolumeCountService {

    @Autowired
    CarVolumeCountDao carVolumeCountDao;

    @Override
    public Map<String, Object> getChartData(String deptId, String dateType, String dateString, String thisDateString) {
        if(ToolUtil.isEmpty(deptId) || ToolUtil.isEmpty(dateType)
                || ToolUtil.isEmpty(dateString) || ToolUtil.isEmpty(thisDateString)) {
            return msgMap(400, "参数不正确", "deptId" + deptId +
                    ",dateType:" + dateType + ",dateString:" + dateString + ",thisDateString:" + thisDateString);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("dateType", dateType);      //因为前台还需要获取查询的时间类型,所以又加到返回结果里面了.
        //添加前一年的统计数据
        dataMap.put("thatFlowData", getFlowChartData(deptId, dateType, dateString));    //车流量
        dataMap.put("thatAxleData", getAxleChartData(deptId, dateType, dateString));    //车轴数
        dataMap.put("thatAreaData", getAreaChartData(deptId, dateType, dateString));    //区域信息统计
        //添加选择的那一年的统计数据
        dataMap.put("thisFlowData", getFlowChartData(deptId, dateType, thisDateString));
        dataMap.put("thisAxleData", getAxleChartData(deptId, dateType, thisDateString));
        dataMap.put("thisAreaData", getAreaChartData(deptId, dateType, thisDateString));
        //返回
        return msgMap(200, "OK", dataMap);
    }

    /**
     * 查询车流量数据
     * @param deptId        用于查询的部门id
     * @param dateType      用于查询的时间类型
     * @param dateString    用于查询的时间
     * @return
     */
    @Override
    public List<Map<String, Object>> getFlowChartData(String deptId, String dateType, String dateString) {

        return carVolumeCountDao.getFlowChartData(deptId, dateType, dateString);
    }

    /**
     *  获取车轴统计信息
     * @param deptId        用于查询的部门id
     * @param dateType      用于查询的时间类型
     * @param dateString    用于查询的时间
     * @return
     */
    @Override
    public List<Map<String, Object>> getAxleChartData(String deptId, String dateType, String dateString) {
        return carVolumeCountDao.getAxleChartData(deptId, dateType, dateString);
    }

    /**
     *  获取区域信息
     * @param deptId        用于查询的部门id
     * @param dateType      用于查询的时间类型
     * @param dateString    用于查询的时间
     * @return
     */
    @Override
    public List<Map<String, Object>> getAreaChartData(String deptId, String dateType, String dateString) {
        return carVolumeCountDao.getAreaChartData(deptId, dateType, dateString);
    }


    /**
     * 生成返回的对象
     */
    public Map<String, Object> msgMap(Integer code, Object message, Object data) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("code", code);
        msg.put("msg", message);
        msg.put("data", data);
        return msg;
    }
}
