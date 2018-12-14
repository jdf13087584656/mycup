package com.jdf.mycups.dao.po;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class OperationLog implements Serializable {
    private Long id;

    private String username; //用户名

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createDate; //操作时间
    //创建getter和setter方法
}