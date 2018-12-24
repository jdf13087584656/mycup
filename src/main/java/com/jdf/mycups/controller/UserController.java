package com.jdf.mycups.controller;

import com.jdf.mycups.annotation.Mylog;
import com.jdf.mycups.dao.po.UserInfo;
import com.jdf.mycups.service.UserService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    @ResponseBody
    @Mylog(value = "获取用户信息")
   @ApiOperation(value = "获取用户信息", notes = "获取所有用户信息", httpMethod = "GET")
    public ResponseEntity getUser(Principal principal){
        try {
            log.info("获取用户"+principal);
            List<UserInfo> user = userService.getUser();
          return   ResponseEntity.ok(user);
        }catch (Exception e){
            return collectionException(e);
        }
    }
//    @PostMapping("/login/form")
//    @ResponseBody
//    public ResponseEntity userLogin(@RequestBody UserInfo userInfo){
//        try {
//            log.info("用户登录");
//
//            List<UserInfo> user = userService.getUser();
//
//          return   ResponseEntity.ok(user);
//        }catch (Exception e){
//            return collectionException(e);
//        }
//    }
    @RequestMapping("/login")
    public String login(){
        return "login-up";

    }
    @RequestMapping("/index")
    public String index_into(){
        return "index";
    }

    @RequestMapping("/login-error")
    public String login_error(){
        return "login-error";
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private ResponseEntity collectionException(Exception e) {
      return   ResponseEntity.ok(e);
    }


}
