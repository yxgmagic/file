package com.zhichao.dal.siteRegistration;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Fixedsite;

/**
 * 固定治超站Dao层
 * @author fengshuonan
 * @Date 2018年1月3日 下午1:38:53
 */
@Repository
public interface FixedsiteDao extends BaseMapper<Fixedsite>{
	
	/**
	 * 根据条件查询相对于固定治超站的列表
	 * @param areacode
	 * @param sitename
	 * @param manager
	 * @param managertel
	 * @return
	 */
	List<Map<String, Object>> selList(
			@Param("userDeptid") String userDeptid,
			@Param("areacode") String areacode,
			@Param("sitename") String sitename,
			@Param("manager") String manager,
			@Param("managertel") String managertel,
			@Param("roadcode") String roadcode);

	
}
