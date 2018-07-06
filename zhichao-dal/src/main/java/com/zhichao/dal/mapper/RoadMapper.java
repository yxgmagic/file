package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.node.ZTreeNodeEntity;
import com.zhichao.beans.guns.Road;


/**
 * <p>
  * 路网信息 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-03
 */
public interface RoadMapper extends BaseMapper<Road> {
	 List<Map<String, Object>> list(  @Param("roadname") String roadname, @Param("areacode") String areacode);
	 List<Map<String, Object>> listSelect(  @Param("roadcode") String roadcode, @Param("areacode") String areacode);
	 
	 Road selectByRoadCode(@Param("roadcode") String roadcode);
	 List<ZTreeNodeEntity> tree();
}