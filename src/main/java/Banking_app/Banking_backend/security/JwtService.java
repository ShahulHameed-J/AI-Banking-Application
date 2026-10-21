package Banking_app.Banking_backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    // Generate the SecretKey from our Base64 secret
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT token
    public String generateToken(String email) {

        Date now = new Date();

        Date expiration = new Date(// 9 + 3600000
                now.getTime() + 1000 * 60 * 60
        ); // 1 hour

        return Jwts.builder().subject(email).issuedAt(now).expiration(expiration).signWith(getSigningKey()).compact();
    }

    // Extract email from JWT
    public String extractEmail(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Check whether token is expired
    public boolean isTokenExpired(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().before(new Date());
    }

    // Validate token
    public boolean isTokenValid(String token, String email) {

        try {

            String extractedEmail = extractEmail(token);

            return extractedEmail.equals(email) && !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }
}	