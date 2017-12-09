package com.easylinker.iot.v2.model.config;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 又拍云的数据库持久化类
 * Created by wwhai on 2017/12/9.
 * 空间名称
 * 用户名
 * 密码
 * 秘钥
 */
@Entity
@Table(name = "UP_YUN_ACCOUNT")
public class UpYunAccount extends BaseEntity {
    private String bucketName;
    private String username;
    private String password;
    private String apiKey;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
