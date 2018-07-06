package com.zhichao.service.report.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.report.IReportsendService;
import com.zhichao.beans.guns.Reportsend;
import com.zhichao.dal.mapper.ReportsendMapper;

/**
 * <p>
 * 抄告信息反馈表 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2018-03-22
 */
@Service
public class ReportsendServiceImpl extends ServiceImpl<ReportsendMapper, Reportsend> implements IReportsendService {
	
}
