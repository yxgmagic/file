package com.zhichao.service.dms.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.dms.IServerParaService;
import com.zhichao.beans.guns.ServerPara;
import com.zhichao.dal.mapper.ServerParaMapper;

/**
 * <p>
 * 服务器参数列表 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-02-26
 */
@Service
public class ServerParaServiceImpl extends ServiceImpl<ServerParaMapper, ServerPara> implements IServerParaService {
	
}
