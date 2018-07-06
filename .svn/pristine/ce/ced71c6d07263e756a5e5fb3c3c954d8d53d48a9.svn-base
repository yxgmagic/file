package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Reportinfo;

/**
 * <p>
  * 抄告内容信息表 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2018-03-22
 */
public interface ReportinfoMapper extends BaseMapper<Reportinfo> {
	List<Map<String, Object>> ListReport(@Param("condition") String condition,@Param("level") String level);
}