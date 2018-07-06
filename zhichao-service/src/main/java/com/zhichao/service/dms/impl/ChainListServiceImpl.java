package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IChainListService;
import com.zhichao.beans.guns.ChainList;
import com.zhichao.dal.mapper.ChainListMapper;

/**
 * <p>
 * 数据服务链列表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@Service
public class ChainListServiceImpl extends ServiceImpl<ChainListMapper, ChainList> implements IChainListService {
	
}
