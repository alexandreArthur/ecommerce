package com.ecommerce.resources;

import com.ecommerce.security.JWTUtil;
import com.ecommerce.security.UserSS;
import com.ecommerce.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping(value = "/refresh_token" )
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = userService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
