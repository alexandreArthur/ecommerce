package com.ecommerce.resources;

import com.ecommerce.dto.EmailDTO;
import com.ecommerce.security.JWTUtil;
import com.ecommerce.security.UserSS;
import com.ecommerce.services.AuthService;
import com.ecommerce.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthService service;


    @PostMapping(value = "/refresh_token" )
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = userService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/forgot" )
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());

        return ResponseEntity.noContent().build();
    }
}
