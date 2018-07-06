package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.LawDoc;

/**
 * <p>
  * 执法文书模板 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-19
 */
public interface LawDocMapper extends BaseMapper<LawDoc> {
	List<Map<String, Object>> listLawDoc(  @Param("ldname") String ldname, @Param("id") Integer id);
	List<Map<String, Object>> listLawDocByTp(  @Param("ldtype") String ldtype, @Param("id") Integer id);
	
	LawDoc getLawDocByTp(  @Param("ldtype") String ldtype);
	
	int setLdStatus( @Param("ldtype") String ldtype, @Param("id") Integer id);
	Integer getMaxLdno(  @Param("ldtype") String ldtype );
	Integer getLdId(  @Param("ldtype") String ldtype );
	Integer getMaxLdId(  @Param("ldtype") String ldtype );
}