package com.example.test.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class JwtServiceImpl implements JwtService{

  // 쿠키 서명을 위한 비밀키
  private String secretKey = "abcdefg123456789!@#abc!@#$%//abcdefg123456789!@#abc!@#$%//abcdefg123456789!@#abc!@#$%";
  
  // 토큰 발급
  @Override
  public String getToken(String key, Object value) {
    
    // 쿠키 만료시간은 현재시간으로부터 60분(1시간) 후
    Date expTime = new Date();
    expTime.setTime(expTime.getTime() + 1000 * 60 * 60);
    
    // 비밀키 디코딩해서 객체 생성
    byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
    
    // 비밀키를 알고리즘에 맞게 설정
    Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
    
    // jwt 헤더 설정
    Map<String, Object> headerMap = new HashMap<>();
    headerMap.put("typ", "JWT"); // 토큰의 유형
    headerMap.put("alg", "HS256"); // 알고리즘
    
    // jwt payload 설정
    Map<String, Object> map = new HashMap<>();
    map.put(key, value); // key 값과 value 값 추가
    
    // JwtBuilder 객체 생성
    JwtBuilder builder = Jwts.builder()
        .setHeader(headerMap) // 헤더
        .setClaims(map) // payload
        .setExpiration(expTime) // 만료시간
        .signWith(signKey, SignatureAlgorithm.HS256); // 비밀키로 서명
        
    // jwt를 생성하고 문자열로 반환하기
    return builder.compact();
  }
  
  // 토큰 검증
  public boolean validateToken(String token) {
 // 비밀키 디코딩해서 객체 생성
    byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
    
    // 비밀키를 알고리즘에 맞게 설정
    Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
    
    try {
      Claims claims= Jwts.parserBuilder()
                          .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)) // jwt를 서명한 키를 설정
                          .build() // parserBuilder에서 설정한 값으로 jwt 파서 객체 생성
                          .parseClaimsJws(token) // jwt 토큰을 파싱해서 Jws<Claims> 객체 반환
                          .getBody(); // parseClaimsJws가 반환한 Jws<Claims> 객체에서 클레임 정보를 추출
      
      Date expirationDate = claims.getExpiration(); // 토큰 만료기간
      if(expirationDate.before(new Date())) return false; // 토큰이 만료된 경우
      return true;
    } catch(ExpiredJwtException e) {
      return false;
    } catch(Exception e) {
      return false;
    }
  }
}
