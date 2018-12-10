package com.jdf.mycups.dao;

import com.jdf.mycups.dao.po.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {

    UserInfo findByName(String username);

    List<UserInfo> getUserInfo();
}
