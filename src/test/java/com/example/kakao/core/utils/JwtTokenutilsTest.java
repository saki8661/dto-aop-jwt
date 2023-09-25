package com.example.kakao.core.utils;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao.user.User;

public class JwtTokenutilsTest {

    @Test
    public void jwt_create_test() {
        User user = User.builder().id(1).email("ssar@naet.com").build();
        String jwt = JwtTokenUtils.create(user);
        System.out.println(jwt);
    }

    @Test
    public void jwt_verify_test() {
        String jwt = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtZXRhY29kaW5nLWtleSIsImlkIjoxLCJlbWFpbCI6InNzYXJAbmFldC5jb20iLCJleHAiOjE2OTYyMzUwNDF9.tfGZdsL_yYWl8SRDl3YIBOjWS1vtIjgNKzf3LS4ovM-8WhERr6qOBN8jfl75RTTrTPd37McKP4HXD_c2ygQLrQ";
        DecodedJWT decodedJWT = JwtTokenUtils.verify(jwt);

        int id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        System.out.println("id : " + id);
        System.out.println("email : " + email);
    }

}
