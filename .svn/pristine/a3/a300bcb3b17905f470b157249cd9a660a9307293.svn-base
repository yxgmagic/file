package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Area;

/**
 * <p>
  * 行政区域信息 Mapper 接口
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
@Repository
public interface AreaMapper extends BaseMapper<Area> {

	 List<Map<String, Object>> listSelect(  @Param("pid") String pid, @Param("areatype") String areatype);
	/**
	 * 查询行政区域管理列表
	 * @param address
	 * @param areatype
	 * @param areaname
	 * @param areacode
	 * @return
	 */
	List<Map<String, Object>> queryAreaByCondition(@Param("address")String address, @Param("areatype")String areatype, @Param("areaname")String areaname, @Param("areacode")String areacode,
			@Param("pid")String pid);
	
	List<Area> selByPid(@Param("pid") Integer pid);
	
	Area selById(Integer areaId);

}