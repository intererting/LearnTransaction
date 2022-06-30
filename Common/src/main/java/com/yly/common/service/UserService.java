package com.yly.common.service;

import com.yly.common.pojo.User;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/29 下午3:24
 * @since 1.0
 */
public interface UserService {

    User getUser(Integer id);

    void updateUser(Integer id);
}
