package com.fil.rouge.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static java.time.LocalTime.now;

@Component
public class JwtUtils {



//    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
//
//
//    @Value("${jwt.expiration}")
//    private int jwtExpirationMs;
//
//    public String generateJwtToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                .compact();
//    }
//
//    public String getUsernameFromJwtToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            System.out.println("Invalid JWT signature: " + e.getMessage());
//        } catch (MalformedJwtException e) {
//            System.out.println("Invalid JWT token: " + e.getMessage());
//        } catch (ExpiredJwtException e) {
//            System.out.println("JWT token is expired: " + e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            System.out.println("JWT token is unsupported: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println("JWT claims string is empty: " + e.getMessage());
//        }
//        return false;
//    }



    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

    //    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, username);
//    }
    public String generateToken(UserDetails userDetails, String role) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                //.setExpiration(Date.from(Instant.from(now().plus(20, ChronoUnit.DAYS))))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


//    private String createToken(Map<String, Object> claims, String username) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(Date.from(Instant.from(now())))
//                .setExpiration(Date.from(Instant.from(now().plus(20, ChronoUnit.DAYS))))
//                .signWith(getSignKey() ,SignatureAlgorithm.HS256)
//                .compact();
//    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
