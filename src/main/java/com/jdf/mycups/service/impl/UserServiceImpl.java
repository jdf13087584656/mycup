package com.jdf.mycups.service.impl;

import com.jdf.mycups.dao.UserInfoDao;
import com.jdf.mycups.dao.po.UserInfo;
import com.jdf.mycups.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserInfoDao userInfoDao;
    @Override
    public  List<UserInfo> getUser() {
        List<UserInfo> userInfo = userInfoDao.getUserInfo();
        return userInfo;
    }
}
