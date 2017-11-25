package com.easylinker.iot.v2.model.user;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "ROLE")
/**
 * 角色名  前端提示  角色描述
 */
public class Role extends BaseEntity {
    private String name;
    private String tip;
    private Integer roleId;

    public Role() {
        super();
    }

    public Role(String name, String tip, Integer roleId) {
        this.name = name;
        this.tip = tip;
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
