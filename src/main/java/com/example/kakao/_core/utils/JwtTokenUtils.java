package com.example.kakao._core.utils;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kakao.user.User;

public class JwtTokenUtils {

    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject("metacoding-key") // 토큰의 이름 = 프로토콜(의미없다)
                .withClaim("id", user.getId()) // ★★ 토큰에 저장하고 싶은 정보 = PK는 꼭 넣어줘야 한다.
                .withClaim("email", user.getEmail()) // 로그인과 관련된 정보를 넣으면 된다.
                .withExpiresAt(Instant.now().plusMillis(1000 * 60 * 60 * 24 * 7L)) // L = LondTime
                .sign(Algorithm.HMAC512("meta")); // 대칭키 또는 공개키 -> 공개키 선택 -> secret key 장소

        return "Bearer " + jwt;
    }

    public static DecodedJWT verify(String jwt)
            throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace("Bearer ", "");

        // 안풀리는 경우 시간만료 , 잘못적음, 내키로 서명안한키

        // JWT를 검증한 후, 검증이 완료되면, header, payload를 base64로 복호화함.
        // String으로 받아도 되는데 객체로 받은 이유는 파싱하기 힘듬
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("meta"))
                .build().verify(jwt);
        return decodedJWT;
    }

}
