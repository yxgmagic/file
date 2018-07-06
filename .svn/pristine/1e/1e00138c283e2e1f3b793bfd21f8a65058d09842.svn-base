package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.beans.guns.BOseinfo;

/**
 * <p>
  * 非现场执法数据表 Mapper 接口
 * </p>
 *
 * @author zhichao
 * @since 2018-04-04
 */
@Repository
public interface BOseinfoMapper extends BaseMapper<BOseinfo> {

    /**
     * 获取list页面的数据
     * @param page 分页
     * @param deptsArr 部门数组
     * @param stationid 站点
     * @param vehicleid 车牌号
     * @param osetime 时间
     * @param begin 开始时间
     * @param end 结束时间
     * @param id 查询详情时id
     * @return
     */
    List<BOseinfo> selList(@Param(value = "page") Page<BOseinfo> page, @Param("depts") String[] depts, @Param("stationid") String stationid,
                           @Param("vehicleid") String vehicleid, @Param("osetime") String osetime, @Param("begin") String begin,
                           @Param("end") String end, @Param("id") Integer id);


    BOseinfo oseInfo(@Param("id") Integer id);

    /**
     * 跳转到非现场数据管理时找图片
     * @param id 主键值
     * @return
     */
    Map<String, String> getVehicleImages(@Param("id") Integer id);

    /**
     * 计算大屏数据(根据站点)
     * @param stationid 站点id
     * @return
     */
    List<Double> calcStation(@Param("stationid") String stationid);

    /**
     * 计算大屏数据(根据部门)
     * @param depts 部门数组
     * @return
     */
    List<Double> calcDept(@Param("depts") String[] depts);


    /**
     * 修改非现场执法中状态
     * @param id 非现场执法数据id
     * @param checkno 检测单号
     * @param oldstatus 旧状态
     * @param newstatus 新状态
     */
    Integer updateProstatus(@Param(value="id") Integer id, @Param(value="checkno") String checkno,
                            @Param(value="oldstatus") Integer oldstatus, @Param(value="newstatus") Integer newstatus);

    /**
     * 根据非现场业务信息表id查询出所有业务信息和站点信息
     * @param id 业务信息表id
     * @return
     */
    Map<String,Object> findOseAndOseSiteInfo(@Param("id") Integer id);
}