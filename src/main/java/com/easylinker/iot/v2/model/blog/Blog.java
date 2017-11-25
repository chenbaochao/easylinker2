package com.easylinker.iot.v2.model.blog;

import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by wwhai on 2017/11/25.
 */
@Entity
@Table(name = "BLOG")
/**
 * 标题
 * 标签
 * 摘要
 * 作者
 * 内容
 * 是否是热帖
 */
public class Blog extends BaseEntity {
    private String title;
    private String tag;
    private String summary;

    @ManyToOne(targetEntity = AppUser.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppUser appUser;
    private String content;
    private boolean isHot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }
}
