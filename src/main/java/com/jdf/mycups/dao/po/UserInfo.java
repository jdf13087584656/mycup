package com.jdf.mycups.dao.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
public class UserInfo implements Serializable ,UserDetails {
    private Integer userid;
    private String username;
    private String password;
    private String phone;
    private String role;

    public UserInfo(Integer userid, String username, String password,String phone) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public UserInfo(String username, String password, String role) {
        // TODO Auto-generated constructor stub
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
