package com.zhichao.service.siteRegistration.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.siteRegistration.IBsOsesiteService;
import com.zhichao.beans.guns.BsOsesite;
import com.zhichao.dal.mapper.BsOsesiteMapper;

/**
 * <p>
 * 非现场执法信息表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-03-29
 */
@Service
public class BsOsesiteServiceImpl extends ServiceImpl<BsOsesiteMapper, BsOsesite> implements IBsOsesiteService {

    @Autowired
    private BsOsesiteMapper osesiteMapper;

    @Override
    public List<Map<String, Object>> list(String sitename, String roadcode, String areacode, Integer id) {
        //从shiro中获取到当前用户的部门号
        String userDeptid = String.valueOf(ShiroKit.getUser().getDeptId());

        //同步检定证书
        FileCopy.synchronizationCertificate();


        return osesiteMapper.list(YUtil.isNullOrEmptyReturnString(sitename,true),
                YUtil.isNullOrEmptyReturnString(roadcode,true),
                YUtil.isNullOrEmptyReturnString(areacode,true),
                id, userDeptid);
    }
}
