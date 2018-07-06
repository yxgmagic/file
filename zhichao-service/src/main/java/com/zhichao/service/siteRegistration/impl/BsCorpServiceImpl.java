package com.zhichao.service.siteRegistration.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.siteRegistration.IBsCorpService;
import com.zhichao.beans.guns.BsCorp;
import com.zhichao.dal.mapper.BsCorpMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
@Service
public class BsCorpServiceImpl extends ServiceImpl<BsCorpMapper, BsCorp> implements IBsCorpService {

	@Autowired
	private BsCorpMapper mapper;
	
	@Override
	public List<BsCorp> queryCorpByCondition(String corpname, String areacode) {
		corpname = corpname == null ? null : corpname.trim();
		areacode = areacode == null ? null : areacode.trim();
		return mapper.queryCorpByCondition(corpname, areacode);
	}

	@Override
	public BsCorp selectById(Integer bsCorpId) {
		return mapper.selById(bsCorpId);
	}

	@Override
	public Integer corpIsExist(String corpcode, Integer id, String managerid) {
		return mapper.corpIsExist(corpcode,id, managerid);
	}
	
}
