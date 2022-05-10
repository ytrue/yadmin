package com.ytrue.yadmin.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;


/**
 * @author ytrue
 * @date 2022/5/5 14:54
 * @description JwtUtilTest
 */
class JwtUtilTest {

    @Test
    void createToken() {

//        LoginUser loginUser = new LoginUser();
//        User user = new User();
//        user.setId(999).setEmail("21213@qq.com").setPassword("123456").setUsername("yangyi");
//
//        loginUser.setUser(user);
//
//        HashMap<String, Object> otherInfo = new HashMap<>(16);
//        otherInfo.put("loginUser", loginUser);
//
//
//        String token = JwtUtil.createToken("123",otherInfo);

        String token ="Bearer#eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwibG9naW5Vc2VyIjp7InVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoidGVzdCIsInBhc3N3b3JkIjoiJDJhJDEwJDdDNVB1UmE4N3JrcEFNTWkxNnBlRnVSUTcyUEkuRkUvMVhkMHlZMXNKNnF1bDhmcE1FSDV5IiwiZW1haWwiOiJ5dHJ1ZUBxcS5jb20ifSwiZW5hYmxlZCI6dHJ1ZSwicGFzc3dvcmQiOiIkMmEkMTAkN0M1UHVSYTg3cmtwQU1NaTE2cGVGdVJRNzJQSS5GRS8xWGQweVkxc0o2cXVsOGZwTUVINXkiLCJhdXRob3JpdGllcyI6bnVsbCwidXNlcm5hbWUiOiJ0ZXN0IiwiYWNjb3VudE5vbkxvY2tlZCI6dHJ1ZSwiYWNjb3VudE5vbkV4cGlyZWQiOnRydWUsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZX0sIm5iZiI6MTY1MTczNzk1MiwiZXhwIjoxNjUxNzM4MTMyLCJpYXQiOjE2NTE3Mzc5NTIsImp0aSI6IlRodSBNYXkgMDUgMTY6MDU6NTIgQ1NUIDIwMjIifQ.cvfHmDwQHnVs4Q5TduO0bOwRVAJmRvqHX5FACu_ZEPY";


        Jws<Claims> claimsJws = JwtUtil.parseToken(token);


    }

}
