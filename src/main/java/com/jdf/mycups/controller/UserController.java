package com.jdf.mycups.controller;

import com.jdf.mycups.dao.po.UserInfo;
import com.jdf.mycups.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity getUser(){
        try {
            log.info("获取用户");
            List<UserInfo> user = userService.getUser();
          return   ResponseEntity.ok(user);
        }catch (Exception e){
            return collectionException(e);
        }
    }

    private ResponseEntity collectionException(Exception e) {
      return   ResponseEntity.ok(e);
    }


}
