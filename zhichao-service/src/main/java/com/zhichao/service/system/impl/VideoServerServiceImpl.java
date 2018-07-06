package com.zhichao.service.system.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.system.IVideoServerService;
import com.zhichao.beans.guns.VideoServer;
import com.zhichao.dal.mapper.VideoServerMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhichao
 * @since 2018-01-24
 */
@Service
public class VideoServerServiceImpl extends ServiceImpl<VideoServerMapper, VideoServer> implements IVideoServerService {
	
}
