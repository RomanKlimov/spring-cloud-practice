package ru.kpfu.itis.web.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {
//    We use JJWT to generate/parse JWT Token.
    private static final String REDIS_SET_ACTIVE_SUBJECTS = "active-subjects";
    private static final String REDIS_SET_BLOCKED_SUBJECTS = "blocked-subjects";

    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        String token = builder.compact();

        RedisUtil.INSTANCE.sadd(REDIS_SET_ACTIVE_SUBJECTS, subject);

        return token;
    }

    public static String parseToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) {
            return null;
        }

        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        if (!RedisUtil.INSTANCE.sismember(REDIS_SET_ACTIVE_SUBJECTS, subject)) {
            return null;
        }

        return subject;
    }

    public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
        RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, (String) httpServletRequest.getAttribute("username"));
    }

    public static void invalidateTokenByUsername(String username) {
        RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, username);
    }

    public static void addToBlackList(String username) {
        RedisUtil.INSTANCE.sadd(REDIS_SET_BLOCKED_SUBJECTS, username);
    }

    public static boolean checkIfUserInBlackList(String username) {
        if (RedisUtil.INSTANCE.sismember(REDIS_SET_BLOCKED_SUBJECTS, username)) {
            return true;
        } else return false;
    }






}

