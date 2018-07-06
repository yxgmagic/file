package com.zhichao.dal.compreAnalysis;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SiteYoyAnalysisDao {

    List<Map<String, Object>> selList(@Param("casetime") String casetime,
                                      @Param("vehicleid") String vehicleid,
                                      @Param("caseno") String caseno,
                                      @Param("prostatus") String prostatus);

    List<Map<String, Object>> selListAxlesum(@Param("casetime") String casetime,
                                             @Param("start") String start,
                                             @Param("end") String end,
                                             @Param("violationlevel") String violationlevel);
    List<Map<String, Object>> selListSite(@Param("casetime") String casetime,
                                             @Param("start") String start,
                                             @Param("end") String end,
                                             @Param("violationlevel") String violationlevel);
    List<Map<String, Object>> selListTable(@Param("casetime") String casetime,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("violationlevel") String violationlevel);

    List<Map<String, Object>> selectNumberPreSite(@Param("hsptime") String hsptime,
                                                  @Param("start") String start,
                                                  @Param("end") String end);

    List<Map<String, Object>> selectNumberFixedSite(@Param("fctime") String fctime,
                                                  @Param("start") String start,
                                                  @Param("end") String end);

    List<Map<String, Object>> selectOverRun(@Param("fctime") String fctime,
                                            @Param("start") String start,
                                            @Param("end") String end);

    List<Map<String, Object>> selectCargoType(@Param("fctime") String fctime,
                                            @Param("start") String start,
                                            @Param("end") String end);
}
