package com.zx.fastbackend.dao;


import com.zx.fastbackend.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author xuyuqin
 * @create 2019-05-07 10:12
 **/
@Repository
public interface UserDao {
    @Select("select *from sys_user where username=#{username}")
    SysUser getUser(@Param("username") String username);

    @Select("select *from sys_user where id=#{id}")
    SysUser getUserById(@Param("id") String id);

    @SelectKey(before = true, resultType = String.class, keyProperty = "id", statement = "select replace(uuid(), '-', '') as id from dual")
    @Insert("insert into sys_user(id,username,password,salt) value(#{id},#{username},#{password},#{salt)")
    int post(SysUser user);
}
