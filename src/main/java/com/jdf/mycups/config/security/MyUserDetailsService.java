package com.jdf.mycups.config.security;

import com.jdf.mycups.dao.UserInfoDao;
import com.jdf.mycups.dao.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByName(name);

        if (userInfo != null) {

            //此处可查询出角色的登信息一起返回
            String username = userInfo.getUsername();


    //            返回用户信息
            return (UserDetails) userInfo;

        }else {
            return null;
        }

    }
}
