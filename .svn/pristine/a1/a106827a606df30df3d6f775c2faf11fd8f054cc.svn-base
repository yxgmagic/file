package com.zhichao.service.compreAnalysis;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ISiteYoyAnalysisService {
    /**
     * 获取站点同比查询列表
     */
    List<Object> selList(@Param("casetime") String casetime,
                         @Param("violationlevel") String violationlevel);

    /**
     * 获取站带你同比查询列表-表格
     */
    List<Map<String, Object>> selListTable(@Param("casetime") String casetime,
                                           @Param("violationlevel") String violationlevel);

    /**
     * 获取车流量
     */
    Object selectNumberSite(@Param("period") String period);

    /**
     * 获取超限量
     */
    Object selectOverRun(@Param("fctime") String fctime);

    /**
     * 获取货物类别
     */
    List<Map<String, Object>> selectCarGoType(@Param("fctime") String fctime);
}
