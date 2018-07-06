package com.zhichao.service.perforevalue;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.PeSiteIndic;

/**
 * <p>
 * 考评指标数据 服务类
 * </p>
 *
 * @author zhichao
 * @since 2018-03-06
 */
public interface IPeSiteIndicService extends IService<PeSiteIndic> {


    /**
     * 考评指标数据查询
     * @param assess_id 考核计划id
     * @param indic_id 指标id
     * @param indicType 指标类型
     * @param sitecode 站点业务编号
     * @return
     */
    List<Map<String,Object>> selList(String assess_id, String indic_id, String indicType, String sitecode);

}
