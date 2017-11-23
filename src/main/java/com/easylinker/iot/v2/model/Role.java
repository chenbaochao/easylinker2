package com.easylinker.iot.v2.model;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {
    private String name;
    private String tip;
    private String roleName;

    public Role() {
        super();
    }

    public Role(String name, String tip, String roleName) {
        this.name = name;
        this.tip = tip;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
