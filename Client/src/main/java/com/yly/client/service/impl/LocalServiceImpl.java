package com.yly.client.service.impl;

import com.yly.client.advice.MyAnnotation;
import com.yly.client.mapper.LocalUserMapper;
import com.yly.client.service.LocalService;
import com.yly.common.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/30 下午1:37
 * @since 1.0
 */
@Service
@Slf4j
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalUserMapper              localUserMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private TransactionDefinition        transactionDefinition;

    @Override
    public User getLocalUser(Integer id) {
        return localUserMapper.getLocalUser(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateUser(Integer id) {
        //这个地方为什么没有回滚,因为事务连接是存在ThreadLocal里面的,MyBatis会从ThreadLocal中获取连接,
        //参考DataSourceUtil,如果没有获取到才新创建,新创建的就没有事务了,所以这个地方回滚不了
//        new Thread(() -> localUserMapper.updateUser(id)).start();
        localUserMapper.updateUser(id);
    }

    @Override
    public void transaction(Integer id) {
        //开启事务
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            localUserMapper.updateUser(id);
            throw new RuntimeException("error");
        } catch (Exception e) {
            transactionManager.rollback(status);
        } finally {
            transactionManager.commit(status);
        }
    }

    @Override
    @MyAnnotation()
    public void aop(Integer id) {
        log.info("aop function invoke");
//        throw new RuntimeException("error");
    }
}
