package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IChainActionService;
import com.zhichao.beans.guns.ChainAction;
import com.zhichao.dal.mapper.ChainActionMapper;

/**
 * <p>
 * 数据服务链步骤表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@Service
public class ChainActionServiceImpl extends ServiceImpl<ChainActionMapper, ChainAction> implements IChainActionService {
	
}
