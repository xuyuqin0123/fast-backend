package com.zx.fastbackend.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author xuyuqin
 * @create 2019-05-07 10:56
 **/
@Entity
@Table(name = "sys_token")
public class SysToken {
    private String id;
    private String userId;
    private String token;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysToken sysToken = (SysToken) o;
        return id == sysToken.id &&
                Objects.equals(userId, sysToken.userId) &&
                Objects.equals(token, sysToken.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, token);
    }
}
