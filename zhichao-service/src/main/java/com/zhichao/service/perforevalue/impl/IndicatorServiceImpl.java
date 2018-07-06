package com.zhichao.service.perforevalue.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.perforevalue.IIndicatorService;
import com.zhichao.beans.guns.Indicator;
import com.zhichao.dal.mapper.IndicatorMapper;

/**
 * <p>
 * 指标管理表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-27
 */
@Service
public class IndicatorServiceImpl extends ServiceImpl<IndicatorMapper, Indicator> implements IIndicatorService {

    @Autowired
    private IndicatorMapper mapper;

    @Override
    public List<Indicator> selList(String indicName, String indicType) {

        //建立查询条件
        EntityWrapper<Indicator> ew = new EntityWrapper<>();
        ew.where("1=1");
        //如果指标名称不为空,执行查询
        if (!YUtil.isNullOrEmpty(indicName, true)){
            ew.and("indicatorName = {0}",indicName);
        }
        //如果指标类型不为空,执行查询
        if (!YUtil.isNullOrEmpty(indicType, true)){
            ew.and("indicatorCategory = {0}",indicType);
        }

        //倒序
        ew.orderBy("id");

        List<Indicator> list = mapper.selectList(ew);

        //修改list中指标类别,评分规则的名称
        if (list != null && list.size() > 0){
            for (Indicator indic : list){
                if (!YUtil.isNullOrEmpty(indic.getIndicatorCategory(), true)){
                    indic.setIndicatorCategory(DictUtil.selectNameByEnameNum("indicType", indic.getIndicatorCategory()));
                }
                if (!YUtil.isNullOrEmpty(indic.getJudgeRules(), true)){
                    indic.setJudgeRules(DictUtil.selectNameByEnameNum("judgeRules", indic.getJudgeRules()));
                }
            }
        }

        return list;
    }
}
