package com.zhichao.service.perforevalue.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.perforevalue.IPeSiteIndicService;
import com.zhichao.beans.guns.PeSiteIndic;
import com.zhichao.dal.mapper.PeSiteIndicMapper;

/**
 * <p>
 * 考评指标数据 服务实现类
 * </p>
 *
 * @author zhichao
 * @since 2018-03-06
 */
@Service
public class PeSiteIndicServiceImpl extends ServiceImpl<PeSiteIndicMapper, PeSiteIndic> implements IPeSiteIndicService {

    @Autowired
    private PeSiteIndicMapper mapper;

    @Override
    public List<Map<String,Object>> selList(String assess_id, String indic_id, String indicType, String sitecode) {
        return mapper.selList(assess_id,indic_id,indicType,sitecode);
    }

}
