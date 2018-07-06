package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Presite;

/**
 * <p>
  * 不停车预检站信息表 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-03
 */
public interface PresiteMapper extends BaseMapper<Presite> {
	 List<Map<String, Object>> list( @Param("userDeptid") String userDeptid,  @Param("sitename") String sitename, @Param("areacode") String areacode, @Param("id") Integer id);
 
}