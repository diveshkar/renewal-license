package com.finalproject.mvpass.config;

import com.finalproject.mvpass.model.RequestMeta;
import com.finalproject.mvpass.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtUtils jwtUtils;
    private final RequestMeta requestMeta;

    @Autowired
    public JwtInterceptor(JwtUtils jwtUtils, RequestMeta requestMeta) {
        this.jwtUtils = jwtUtils;
        this.requestMeta = requestMeta;
    }

//    @CrossOrigin
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }

        List<String> PUBLIC_PATHS = Arrays.asList("/api/v1/user/login", "/api/v1/user/register", "/api/v1/admin/login");

        if (request.getRequestURI().startsWith("/api/v1/admin/renewal/getImage/")) {
            // Continue with the request without authentication check
            return true;
        }

        if (PUBLIC_PATHS.stream().noneMatch(request.getRequestURI()::contains)) {
                Claims claims = jwtUtils.verify(auth);

                if (claims != null) {

                    requestMeta.setNic(claims.getIssuer());
                    requestMeta.setLicenceno(claims.get("LicenseNo"));
                    requestMeta.setEmail(claims.get("emailId"));
                    requestMeta.setAdminname(claims.get("adminname"));
                    requestMeta.setRole(claims.get("Role"));

                    // Log the claims
                    logger.info("emailIdObject: {}", requestMeta.getEmail());
                    logger.info("licenseNoObject: {}", requestMeta.getLicenceno());
                    logger.info("nicObject: {}", requestMeta.getNic());
                    logger.info("adminUserNameObject: {}", requestMeta.getAdminname());
                    logger.info("RoleObject: {}", requestMeta.getRole());
                }
            }


        // Continue with the request
        return true;
    }
}
