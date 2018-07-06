package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Reportservice;

/**
 * <p>
  * 抄告对象信息表 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2018-03-16
 */
public interface ReportserviceMapper extends BaseMapper<Reportservice> {
	List<Map<String, Object>> findList(@Param("reportName") String reportName);
}