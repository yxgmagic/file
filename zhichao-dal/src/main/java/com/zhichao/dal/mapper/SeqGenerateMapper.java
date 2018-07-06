package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.SeqGenerate;

/**
 * <p>
  * 编码生成规则表 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-08
 */
public interface SeqGenerateMapper extends BaseMapper<SeqGenerate> {
	  List<Map<String, Object>> getSeqGenBytype(  @Param("seqtype") String seqtype );
}