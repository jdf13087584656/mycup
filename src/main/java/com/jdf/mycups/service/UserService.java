package com.jdf.mycups.service;

import com.jdf.mycups.dao.po.UserInfo;

import java.util.List;

public interface UserService {
  List<UserInfo> getUser();
}
