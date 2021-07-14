package com.ecommerce.services;

import com.ecommerce.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class userService {

    public static UserSS authenticated(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }catch (Exception e){
            return null;
        }
    }
}
