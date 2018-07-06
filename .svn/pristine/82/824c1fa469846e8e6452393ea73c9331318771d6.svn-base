package com.zhichao.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhichao.beans.constant.Enumeration;
import com.zhichao.dal.mapper.UserMapper;
import com.zhichao.service.system.ICustomerDictService;
import com.zhichao.beans.system.DictKey;
import com.zhichao.beans.guns.User;

/**
 * 字典服务
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/12/18
 */
@Service(value = "customerDictService")
public class CustomerDictServiceImpl implements ICustomerDictService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Enumeration> searchDictByKey(String key, String params) {
        List<Enumeration> list = null;
        switch (key) {
            case DictKey.DICT_SYS_USER:
                list = searchSystemUserList(params);
                break;
        }
        return list;
    }

    private List<Enumeration> searchSystemUserList(String params) {
        Map json = (Map) JSONObject.parse(params);
        List<Enumeration> list = new ArrayList<>();
        List<User> userList = userMapper.selectList(null);
        if (null != userList && 0 != userList.size()) {
            for (User user : userList) {
                Enumeration enumeration = new Enumeration();
                enumeration.setCode(user.getId().toString());
                enumeration.setName(user.getAccount());
                list.add(enumeration);
            }
        }
        return list;
    }
}
