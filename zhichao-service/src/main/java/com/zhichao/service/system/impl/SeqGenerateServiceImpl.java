package com.zhichao.service.system.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.dal.mapper.SeqGenerateMapper;
import com.zhichao.service.system.ISeqGenerateService;
import com.zhichao.beans.guns.SeqGenerate;

/**
 * <p>
 * 编码生成规则表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-01-08
 */
@Service
public class SeqGenerateServiceImpl extends ServiceImpl<SeqGenerateMapper, SeqGenerate> implements ISeqGenerateService {
	
}
