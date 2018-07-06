package com.zhichao.service.system;

import com.zhichao.beans.constant.Enumeration;

import java.util.List;

/**
 * 字典服务
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/12/18
 */
public interface ICustomerDictService {
    List<Enumeration> searchDictByKey(String key, String params);
}
