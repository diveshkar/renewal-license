package com.finalproject.mvpass.util;

import com.finalproject.mvpass.common.ErrorHandle;
import com.finalproject.mvpass.entity.Admin;
import com.finalproject.mvpass.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;



@Component
public class JwtUtils {

    private static String secret = "DiveshHansiTivinBanuMegha";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(User user, Admin admin){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getNic())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);


        // optional claims
        claims.put("emailId", user.getEmail());
        claims.put("LicenseNo", user.getLicenceno());
        claims.put("adminname", admin.getAdminname());
        claims.put("Role", admin.getRole());
//        claims.put("Status", HttpStatus.valueOf(200));

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
//            System.out.println(claims.get("emailId"));
            return claims;
        } catch(Exception e) {
            throw new ErrorHandle("Access Denied");
        }

    }

}