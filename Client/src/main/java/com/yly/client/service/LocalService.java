package com.yly.client.service;

import com.yly.common.pojo.User;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/30 下午1:36
 * @since 1.0
 */
public interface LocalService {

    User getLocalUser(Integer id);

    void updateUser(Integer id);

    void transaction(Integer id);

    void aop(Integer id);
}
