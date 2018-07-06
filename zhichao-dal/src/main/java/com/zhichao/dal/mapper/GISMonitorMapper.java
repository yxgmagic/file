package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.GISMonitor;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
public interface GISMonitorMapper extends BaseMapper<GISMonitor> {

    List<Map<String, Object>> selOverRunInfo();
    List<Map<String, Object>> selOverRunInfos();
}