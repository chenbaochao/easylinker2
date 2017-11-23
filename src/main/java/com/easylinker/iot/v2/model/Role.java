package com.easylinker.iot.v2.model;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {
    private String name;
    private String tip;
    private String roleName;
    @ManyToOne(targetEntity = AppUser.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AppUser appUser;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
