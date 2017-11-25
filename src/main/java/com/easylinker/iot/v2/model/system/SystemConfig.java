package com.easylinker.iot.v2.model.system;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "SYSTEM_CONFIG")
public class SystemConfig extends BaseEntity {
    private String mqUrl;
    private String dbUrl;
    private String dbPassword;
    private String dbUserName;
    private String dbName;
    private String emqBasicUsername;
    private String emqBasicPassword;

    public String getEmqBasicPassword() {
        return emqBasicPassword;
    }

    public void setEmqBasicPassword(String emqBasicPassword) {
        this.emqBasicPassword = emqBasicPassword;
    }

    public String getEmqBasicUsername() {
        return emqBasicUsername;
    }

    public void setEmqBasicUsername(String emqBasicUsername) {
        this.emqBasicUsername = emqBasicUsername;
    }

    public String getMqUrl() {
        return mqUrl;
    }

    public void setMqUrl(String mqUrl) {
        this.mqUrl = mqUrl;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
