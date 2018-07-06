package com.zhichao.dal.platformConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhichao.beans.node.ZTreeNodeArea;
import com.zhichao.beans.guns.Area;

/**
 * 所有区域的信息管理Dao
 * @author fengshuonan
 * @Date 2018年1月2日
 */
public interface AreaDao {
	
	List<Map<String, Object>> selectMunicipalList(@Param("areaname") String areaname,
			@Param("areacode") String areacode,
			@Param("areatype") String areatype);
	List<ZTreeNodeArea> tree( @Param("userid") Integer userid);
	
	Area selectByAreaCode(@Param("areacode") String areacode);
	
	List<Map<String, Object>> selectAreaS();
}
