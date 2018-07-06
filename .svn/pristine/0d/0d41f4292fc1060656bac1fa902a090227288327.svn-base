package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.PeAssessplan;
import com.zhichao.beans.guns.QueryAssessInfoBO;
import com.zhichao.beans.guns.QuerySiteOrIndicBO;

/**
 * <p>
  * 考核计划表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-02-28
 */
public interface PeAssessplanMapper extends BaseMapper<PeAssessplan> {

    /**
     * 根据考核id查询已选择站点和未选择站点
     * @param assess_id 考核id
     * @param not 是添加还是修改
     * @return
     */
    List<QuerySiteOrIndicBO> selSite(@Param("assess_id") String assess_id, @Param("nt") String not);

    /**
     * 根据考核id查询已选择指标和未选择指标
     * @param assess_id 考核id
     * @param not 是添加还是修改
     * @return
     */
    List<QuerySiteOrIndicBO> selIndic(@Param("assess_id") String assess_id, @Param("nt") String not);

    /**
     * 插入站点数据
     * @param assess_id
     * @param site_id
     */
    void insertSite(@Param("assess_id") Integer assess_id,  @Param("site_id") String site_id);

    /**
     * 插入指标数据
     * @param assess_id
     * @param indic_id
     */
    void insertIndic(@Param("assess_id") Integer assess_id, @Param("indic_id") String indic_id);

    /**
     * 根据考核id查询考核详情
     * @param peAssessplanId
     * @return
     */
    Map<String, Object> selById(Integer peAssessplanId);

    /**
     * 清空所有站点 & 指标 与考核计划之间的关系
     * @param assess_id 考核计划id
     */
    void delAllSiteOrIndicRela(@Param("assess_id") Integer assess_id, @Param("rela") String rela);

    /**
     * 查询总数
     * @param id
     * @return
     */
    Integer selCount(@Param("assess_id") Integer id, @Param("tableName") String tableName);

    /**
     * 根据站点id和计划id查询指标总分
     * @param assessId 指标id
     * @param siteId 站点id(不是sitecode)
     * @return 总分
     */
    Integer sumScoreBySiteIdAndAssessId(@Param("assid") String assessId, @Param("siteId") Integer siteId,
                                        @Param("start") String start, @Param("end") String end);

    /**
     * 根据计划id查询计划下面的站点
     * @param assessId
     * @return
     */
    List<QueryAssessInfoBO> queryAssessInfo(@Param("assid") String assessId);
}