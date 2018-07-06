package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.OefullLawdoc;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-23
 */
public interface OefullLawdocMapper extends BaseMapper<OefullLawdoc> {
	OefullLawdoc getOefullLawdoc(  @Param("oefullId") Integer oefullId, @Param("ldtype") String ldtype );
	List<Map<String, String>> getoefullinfo(   @Param("oefullId") Integer oefullId);
	List<Map<String, Object>> selectOefullInfo(  @Param("ldtype") String ldtype, @Param("procstatus") String procstatus, @Param("id") Integer id);

}