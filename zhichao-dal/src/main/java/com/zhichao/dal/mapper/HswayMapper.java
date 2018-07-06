package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Hsway;

/**
 * <p>
  * 高速出入口信息表 Mapper 接口
 * </p>
 *
 * @author zhichao
 * @since 2018-01-02
 */
public interface HswayMapper extends BaseMapper<Hsway> {
	/**
	 * 高速出入口查询
	 * @param hsRoadName
	 * @param hsWayName
	 * @return
	 */
	List<Map<String, Object>> findList(@Param("hsRoadName") String hsRoadName, @Param("hsWayName") String hsWayName);
}