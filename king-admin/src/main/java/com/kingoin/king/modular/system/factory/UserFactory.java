package com.kingoin.king.modular.system.factory;

import com.kingoin.king.modular.system.transfer.UserDto;
import com.kingoin.king.modular.system.model.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author jack
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
