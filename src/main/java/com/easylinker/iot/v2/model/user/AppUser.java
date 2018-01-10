package com.easylinker.iot.v2.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.easylinker.iot.v2.model.base.BaseEntity;
import com.easylinker.iot.v2.model.device.Device;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by wwhai on 2017/11/15.
 */
@JsonIgnoreProperties("deviceList")
@Entity
@Table(name = "USER")
public class AppUser extends BaseEntity implements UserDetails {


    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private String avatar = "/avatar/avatar" + new Random().nextInt(51) + ".png";
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @JsonIgnore
    @JSONField(serialize = false)
    @OneToMany(targetEntity = Device.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> deviceList = new ArrayList<>();
    /**
     * 用户的级别
     * 1 普通用户
     * 2 付费用户
     * 3 高级用户
     */
    private Integer level = 1;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    @JsonIgnore
    @OneToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Role> roleList = new ArrayList<>();

    public AppUser() {
        roleList.add(new Role("USER", "普通用户", 1));
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * 默认给了一个普通用户
         */
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return simpleGrantedAuthorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @JsonIgnore
    public List<Device> getDeviceList() {
        return deviceList;
    }

    @JsonIgnore
    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

}
