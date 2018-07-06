package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IServerListService;
import com.zhichao.beans.guns.ServerList;
import com.zhichao.dal.mapper.ServerListMapper;

/**
 * <p>
 * 数据节点终端配置表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@Service
public class ServerListServiceImpl extends ServiceImpl<ServerListMapper, ServerList> implements IServerListService {
	
}
