package com.mmall.demo2.service.impl;

import com.mmall.demo2.mapper.UserMapper;
import com.mmall.demo2.model.User;
import com.mmall.demo2.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
