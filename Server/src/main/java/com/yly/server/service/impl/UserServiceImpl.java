package com.yly.server.service.impl;

import com.yly.common.pojo.User;
import com.yly.common.service.UserService;
import com.yly.server.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/29 下午3:25
 * @since 1.0
 */
@DubboService
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(Integer id) {
        User user = userMapper.getUser(id);
        System.out.println(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateUser(Integer id) {
        userMapper.updateUser(id);
        //模拟undo_logo表的数据
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("error");
    }
}
