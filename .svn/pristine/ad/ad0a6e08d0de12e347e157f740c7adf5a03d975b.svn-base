package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.LawEnforcecar;

/**
 * <p>
  * 执法车信息 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2018-01-03
 */
public interface LawEnforcecarMapper extends BaseMapper<LawEnforcecar> {
	/**
	 * 移动执法车查询
	 * @param siteName
	 * @param carNumber
	 * @return
	 */
	List<Map<String, Object>> findList(@Param("deptName") String deptName, @Param("carNumber") String carNumber);
}