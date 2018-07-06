package com.zhichao.admin.core;

import java.util.ArrayList;
import java.util.List;

import com.zhichao.service.common.constant.Const;
import com.zhichao.admin.config.properties.GunsProperties;
import com.zhichao.beans.node.MenuNode;
import com.zhichao.core.util.SpringContextHolder;

/**
 * api接口文档显示过滤
 *
 * @author fengshuonan
 * @date 2017-08-17 16:55
 */
public class ApiMenuFilter extends MenuNode {


    public static List<MenuNode> build(List<MenuNode> nodes) {

        //如果关闭了接口文档,则不显示接口文档菜单
        GunsProperties GunsProperties = SpringContextHolder.getBean(GunsProperties.class);
        if (!GunsProperties.getSwaggerOpen()) {
            List<MenuNode> menuNodesCopy = new ArrayList<>();
            for (MenuNode menuNode : nodes) {
                if (Const.API_MENU_NAME.equals(menuNode.getName())) {
                    continue;
                } else {
                    menuNodesCopy.add(menuNode);
                }
            }
            nodes = menuNodesCopy;
        }

        return nodes;
    }
}
