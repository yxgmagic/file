package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IServerLogService;
import com.zhichao.beans.guns.ServerLog;
import com.zhichao.dal.mapper.ServerLogMapper;

/**
 * <p>
 * 数据服务终端日志 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-26
 */
@Service
public class ServerLogServiceImpl extends ServiceImpl<ServerLogMapper, ServerLog> implements IServerLogService {
	
}
