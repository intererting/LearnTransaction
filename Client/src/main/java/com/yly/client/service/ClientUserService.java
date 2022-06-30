package com.yly.client.service;

import com.yly.common.pojo.User;
import com.yly.common.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/30 上午10:37
 * @since 1.0
 */
@Service
public class ClientUserService {

    @DubboReference(interfaceClass = UserService.class)
    UserService userService;

    private final LocalService localService;

    public ClientUserService(LocalService localService) {
        this.localService = localService;
    }

    public User getUser(Integer id) {
        User user = userService.getUser(id);
        System.out.println(user);
        return localService.getLocalUser(id + 1);
    }

    @GlobalTransactional
    public void updateUser(Integer id) {
        localService.updateUser(id);
        userService.updateUser(id + 1);
    }
}
