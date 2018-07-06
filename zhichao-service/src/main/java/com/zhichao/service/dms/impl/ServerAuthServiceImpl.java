package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IServerAuthService;
import com.zhichao.beans.guns.ServerAuth;
import com.zhichao.dal.mapper.ServerAuthMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhichao
 * @since 2018-02-25
 */
@Service
public class ServerAuthServiceImpl extends ServiceImpl<ServerAuthMapper, ServerAuth> implements IServerAuthService {
	
}
