package com.zx.fastbackend.entity;

/**
 * @author xuyuqin
 * @create 2019-04-28 22:44
 **/
public class SysToken {
    private String id;
    private String token;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
