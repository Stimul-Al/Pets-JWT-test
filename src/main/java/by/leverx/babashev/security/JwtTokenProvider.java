package by.leverx.babashev.security;

import by.leverx.babashev.exception.EntityIsNotFoundException;
import by.leverx.babashev.exception.ExpiredTokenException;
import by.leverx.babashev.model.Person;
import by.leverx.babashev.repository.PersonRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final static Integer VALID_PERIOD = 60 * 60 * 1000;
    private final static String ROLE_PREFIX = "ROLE_";

    @Value("${jwt.secret}")
    private String secret;

    private final PersonRepository personRepository;

    public String createToken(String email) {
        Person foundPerson = personRepository.findByEmail(email)
                .orElseThrow(() -> new EntityIsNotFoundException("User not found"));

        Claims claims = Jwts.claims();

        claims.put("email", foundPerson.getEmail());
        claims.put("role", ROLE_PREFIX + foundPerson.getRole().name());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + VALID_PERIOD);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Date expirationDate = claims.getBody().getExpiration();
            if (expirationDate.before(new Date())) {
                throw new ExpiredTokenException("Token is already expired");
            }

            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String resolveToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader("Authorization");

        if (header == null) {
            return null;
        }
        return header.replace("Bearer ", "");
    }

    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        JwtPrincipal principal = new JwtPrincipal(claims.getBody().get("email", String.class),
                                                    claims.getBody().get("role", String.class));

        return new JwtAuthentication(principal, true);
    }
}