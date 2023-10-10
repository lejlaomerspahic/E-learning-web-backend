package elearning.app.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import elearning.app.model.User;
import elearning.app.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static final String SECRET_KEY = "notGoodIdeaLeavingThisSeenButWhatever";
    private static final Integer TOKEN_VALIDITY = 3600 * 5;
    private final UserRepository _userRepository;

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Long getUserIdFromToken(String token) {
        Long userId = getClaimFromToken(token, claims -> {
            return claims.get("userId", Long.class);
        });
        return userId;
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date date = getExpirationDateFromToken(token);
        return date.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        User usr = this._userRepository.findByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usr.getRole().getName());
        claims.put("id", usr.getId());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
