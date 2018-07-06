package com.zhichao.service.road.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.dal.mapper.RoadMapper;
import com.zhichao.service.road.IRoadService;
import com.zhichao.beans.guns.Road;

/**
 * <p>
 * 路网信息 服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-01-03
 */
@Service
public class RoadServiceImpl extends ServiceImpl<RoadMapper, Road> implements IRoadService {
	
}
