package cd.presenceless.authenticationservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.util.Map;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String SECRET;

    public boolean validateToken(final String token) {
        String extractedToken = token.substring("Bearer ".length());

        Claims claims = Jwts.
                parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(extractedToken)
                .getBody();

        return switch (claims) {
            case null -> false;
            case Exception ignored -> false;
            default -> true;
        };
    }

    String createToken(String userName, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
