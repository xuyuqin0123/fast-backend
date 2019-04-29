package com.zx.fastbackend.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author xuyuqin
 * @create 2019-04-28 21:03
 **/
public class test {
    @Test
    public void test(){
        System.out.println(new Md5Hash("123456789","dskjdskdjsldksldksljdksdjks").toBase64());
        System.out.println(new Md5Hash("123","123"));

    }
}
