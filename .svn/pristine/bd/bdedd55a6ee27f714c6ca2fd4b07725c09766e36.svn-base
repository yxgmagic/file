package com.zhichao.service.siteRegistration;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.BsCorp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
public interface IBsCorpService extends IService<BsCorp> {
	
	/**
	 * 查询源头企业管理方法
	 * @param corpname 企业名称
	 * @param areaname 所属区域
	 * @return
	 */
	List<BsCorp> queryCorpByCondition(String corpname, String areaname);
	
	/**
	 * 查询单个
	 * @param bsCorpId
	 * @return
	 */
	BsCorp selectById(Integer bsCorpId);

	Integer corpIsExist(String corpcode, Integer id, String managerid);

}
