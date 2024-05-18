package com.coders.rentkun.security;

import com.coders.rentkun.entities.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${rentkun.app.secret}")
    private String APP_SECRET;

    @Value("${rentkun.expires.in}")
    private long EXPIRES_IN;

    public String generateToken(Authentication auth) {
        User user = (User) auth.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUser(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(APP_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    protected String extractUser(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
