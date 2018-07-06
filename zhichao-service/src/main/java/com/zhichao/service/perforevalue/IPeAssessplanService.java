package com.zhichao.service.perforevalue;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.PeAssessplan;
import com.zhichao.beans.guns.QueryAssessInfoBO;

/**
 * <p>
 * 考核计划表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-28
 */
public interface IPeAssessplanService extends IService<PeAssessplan> {

    /**
     * 查询考核计划列表
     * @param assessName
     * @param assessTime
     * @return
     */
    List<PeAssessplan> selList(String assessName, String assessTime);

    /**
     * 创建考核计划
     * @param assessName 考核名称
     * @param assessTime 考核时间区间
     * @param notes 考核备注
     */
    void save(String assessName, String assessTime, String assessObj, String assessIndic, String notes);

    /**
     * 修改考核计划内容
     * @param id 考核id
     * @param assessName 考核名称
     * @param assessTime 考核时间区间
     * @param notes 考核备注
     */
    void edit(Integer id, String assessName, String assessTime,String assessObj, String assessIndic, String notes);

    /**
     * 根据id查找考核计划详情
     * @param peAssessplanId
     * @return
     */
    Map<String, Object> selById(Integer peAssessplanId);

    /**
     * 返回站点或指标
     * @param tp addSite & addIndic
     * @param page add & edit
     * @param id editid
     * @return
     */
    Map<String,Object> selSiteOrIndic(String tp, String page, String id);

    /**
     * 进入站点或指标选择器之后,查找left和right组件的值
     * @param tp
     * @param id
     * @return
     */
    Map<String,Object> getSelData(String tp, String id);


    /**
     * 查询考核计划数据详情
     * @param assId
     * @return
     */
    List<QueryAssessInfoBO> selAssessInfoByAssessId(String assId, String start, String end);
}
