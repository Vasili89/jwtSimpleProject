package by.kastapravau.jwtapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtils {

    @Value("${jwt.token.secret}")
    private String jwtTokenSecret;

    @Value("${jwt.token.expiration}")
    private Long expiration;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenUtils(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String login) {
        Date now = new Date();
        Date validDate = new Date(now.getTime() + expiration);
        return Jwts.builder()
                    .setSubject(login)
                    .setIssuedAt(now)
                    .setExpiration(validDate)
                    .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
                    .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtTokenSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String jwtToken) {
        String login = getLoginByToken(jwtToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return authToken;
    }

    private String getLoginByToken(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtTokenSecret).parseClaimsJws(jwtToken).getBody().getSubject();
    }
}
