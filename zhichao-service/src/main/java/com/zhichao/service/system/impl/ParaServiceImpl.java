package com.zhichao.service.system.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.dal.mapper.ParaMapper;
import com.zhichao.service.system.IParaService;
import com.zhichao.beans.guns.Para;

/**
 * <p>
 * 系统参数表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-01-15
 */
@Service
public class ParaServiceImpl extends ServiceImpl<ParaMapper, Para> implements IParaService {
	
}
