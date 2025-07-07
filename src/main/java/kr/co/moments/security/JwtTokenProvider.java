package kr.co.moments.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private final String secretKey = "ThisIsAReallyLongSecureSecretKeyForJWTThatIsAtLeast32Bytes";
    private final long accessTokenValidTime = 1000L * 3; //1000L * 60 * 15; // 15분
    private final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7; // 7일

    // Access Token 발급
    public String createAccessToken(String email) {
        return createToken(email, accessTokenValidTime);
    }
    
    // Refresh Token 발급
    public String createRefreshToken(String email) {
        return createToken(email, refreshTokenValidTime);
    }

    private String createToken(String email, long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
    }
    
    // 토큰에서 username(email) 추출
    public String getUserEmail(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
