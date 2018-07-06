package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.PeSiteIndic;

/**
 * <p>
  * 考评指标数据 Mapper 接口
 * </p>
 *
 * @author zhichao
 * @since 2018-03-06
 */
@Repository
public interface PeSiteIndicMapper extends BaseMapper<PeSiteIndic> {

    /**
     * 考评指标数据查询
     * @param assess_id 考核计划id
     * @param indic_id 指标id
     * @param indicType 指标类型
     * @param sitecode 站点业务编号
     * @return
     */
    List<Map<String,Object>> selList(@Param("assess_id") String assess_id, @Param("indic_id") String indic_id,
                      @Param("indicType") String indicType, @Param("sitecode") String sitecode);
}