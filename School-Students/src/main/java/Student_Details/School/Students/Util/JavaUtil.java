package Student_Details.School.Students.Util;

import Student_Details.School.Students.Model.Teachers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JavaUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    public String generateToken(Teachers teacher) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("teacherId", teacher.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(teacher.getUserName())  // username in subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public Integer extractTeacherId(String token) {
        return Integer.parseInt(getAllClaimsFromToken(token).get("teacherId").toString());
    }

    public String extractUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
