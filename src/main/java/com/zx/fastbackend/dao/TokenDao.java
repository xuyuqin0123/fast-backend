package com.zx.fastbackend.dao;

import com.zx.fastbackend.entity.SysToken;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TokenDao {
    @Select("select user_id from sys_token where token = #{token}")
    String getUserIdByToken(@Param("token") String token);


    @SelectKey(before = true, resultType = String.class, keyProperty = "id", statement = "select replace(uuid(), '-', '') as id from dual")
    @Insert("insert into sys_token(id,token,user_id) value(#{id},#{token},#{userId})")
    int post(SysToken sysToken);

}
