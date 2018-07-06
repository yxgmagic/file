package com.zhichao.dal.compreAnalysis;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarVolumeCountDao {

    /**
     * 根据时间类型查询不同的车流量信息
     * @param deptId
     * @param dateType
     * @param dateString
     * @return
     */
    List<Map<String, Object>> getFlowChartData(@Param("deptId") String deptId,
                                               @Param("dateType") String dateType,
                                               @Param("dateString") String dateString);

    /**
     * 根据时间类型查询不同的车型信息
     * @param deptId
     * @param dateType
     * @param dateString
     * @return
     */
    List<Map<String, Object>> getAxleChartData(@Param("deptId") String deptId,
                                               @Param("dateType") String dateType,
                                               @Param("dateString") String dateString);

    /**
     * 根据时间的不同类型查询车牌所属区域的分布情况
     * @param deptId
     * @param dateType
     * @param dateString
     * @return
     */
    List<Map<String, Object>> getAreaChartData(@Param("deptId") String deptId,
                                               @Param("dateType") String dateType,
                                               @Param("dateString") String dateString);

}
