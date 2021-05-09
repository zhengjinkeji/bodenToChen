package com.kingoin.king.core.util;

import com.kingoin.king.core.common.constant.Const;
import com.kingoin.king.config.properties.KingProperties;
import com.kingoin.king.core.node.MenuNode;

import java.util.ArrayList;
import java.util.List;

/**
 * api接口文档显示过滤
 *
 * @author jack
 * @date 2017-08-17 16:55
 */
public class ApiMenuFilter extends MenuNode {

    public static List<MenuNode> build(List<MenuNode> nodes) {

        //如果关闭了接口文档,则不显示接口文档菜单
        KingProperties kingProperties = SpringContextHolder.getBean(KingProperties.class);
        if (!kingProperties.getSwaggerOpen()) {
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
