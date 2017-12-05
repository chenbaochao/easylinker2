package com.easylinker.iot.v2.model.emq;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/12/5.
 * 集群
 */
@Entity
@Table(name = "EMQ_GROUP")
public class EmqGroup extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
