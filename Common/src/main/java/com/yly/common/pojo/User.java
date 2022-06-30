package com.yly.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/29 下午3:21
 * @since 1.0
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String email;
}
