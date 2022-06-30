package com.yly.client.controller;

import com.yly.client.service.ClientUserService;
import com.yly.client.service.LocalService;
import com.yly.common.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/30 上午10:36
 * @since 1.0
 */
@RestController
public class UserController {

    private final ClientUserService clientUserService;

    private final LocalService localService;

    public UserController(ClientUserService clientUserService, LocalService localService) {
        this.clientUserService = clientUserService;
        this.localService = localService;
    }

    @GetMapping("/user")
    User getUser(@RequestParam Integer id) {
        return clientUserService.getUser(id);
    }

    @PutMapping("/user")
    void updateUser(@RequestParam Integer id) {
        clientUserService.updateUser(id);
    }

    @PutMapping("/transaction")
    void transaction(@RequestParam Integer id) {
        localService.transaction(id);
    }
}





















