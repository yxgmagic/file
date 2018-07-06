package com.zhichao.dal.platformConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 执法人员管理Dao层
 * @author fengshuonan
 * @Date 2018年1月3日 下午7:46:54
 */
@Repository
public interface LawEnforceManDao {
	
	List<Map<String, Object>> selectList(@Param("areacode") String areacode,
			@Param("lemName") String lemName,
			@Param("lawEnforcementAgencies") String lawEnforcementAgencies);

	/**
	 * 检查执法人员身份证是否存在
	 * @param lemIdCardNum	省份证号码
	 * @param lemNum	执法人员编号
	 * @param lemId		执法人员id
	 * @return
	 */
	Integer check(@Param("lemIdCardNum") String lemIdCardNum,
			@Param("lemNum") String lemNum,
			@Param("lemId") String lemId);
}
