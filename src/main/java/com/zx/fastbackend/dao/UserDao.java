package com.zx.fastbackend.dao;


import com.zx.fastbackend.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xuyuqin
 * @create 2019-05-07 10:12
 **/
@Repository
public interface UserDao {
    @Select("select *from sys_user where username=#{username}")
    SysUser getUser(@Param("username") String username);
}
