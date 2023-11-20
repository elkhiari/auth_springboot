package com.eclipse.blogging.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {

    private static String SECRET_KEY = "ZGnBUGNqPFCFEsGlBxop5dUHWgZg579uqg8QF9TNuixwsePo19kprO7mJjt1OaGRp/XUeqXC8uraD3CX3Q48F3gUUrqhiqDkMHstTb7i+tSm20BImuexHWGVwkdNGDe40niHY2XpFhNQT64RQQba6uZYhMj1IgHLl4Q5uKHCzU5sAp4xX6ARROw2lMcJizgGmfGs9A/aZMlJ2xAXqynZsv6WVBp6Q+LMYKKA+chH/a034iUVNMYFIYqr8rfqxdcVuv5yCbKsGA1nCieHJXlJtayUk5W5K6KwW3d1OzDzFF4Q2kjO3QSpekI6Oq+ci0PTWg8QDVt3r2qqWBDeB/cU4fXTw3iGS8BUNt1X/cL/i5E= ";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keybyte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybyte);
    }
}
