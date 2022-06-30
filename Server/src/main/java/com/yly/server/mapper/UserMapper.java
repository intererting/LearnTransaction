package com.yly.server.mapper;

import com.yly.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/29 下午3:26
 * @since 1.0
 */
@Mapper
public interface UserMapper {

    User getUser(@Param("id") Integer id);

    void updateUser(@Param("id") Integer id);
}
