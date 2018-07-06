package com.zhichao.admin.factory.system;

import org.springframework.beans.BeanUtils;

import com.zhichao.beans.transfer.UserDto;
import com.zhichao.beans.guns.User;

/**
 * 用户创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
