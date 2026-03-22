package ra.kienpc.session13.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expired}")
    private Long expired;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // Sinh token
    public String generateAccessToken(String username) { // Thoi han token 1 ngay
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expired))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) { // Thoi han lau hon 7 ngay
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expired * 7))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Xac minh token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid token ", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            log.error("Expired token ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported token ", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Jwt key string invalid ", e.getMessage());
        }
        return false;
    }

    // Giai ma token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }
}
