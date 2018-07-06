package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IServerAuthChainService;
import com.zhichao.beans.guns.ServerAuthChain;
import com.zhichao.dal.mapper.ServerAuthChainMapper;

/**
 * <p>
 * 数据服务终端与数据服务链关系表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@Service
public class ServerAuthChainServiceImpl extends ServiceImpl<ServerAuthChainMapper, ServerAuthChain> implements IServerAuthChainService {
	
}
