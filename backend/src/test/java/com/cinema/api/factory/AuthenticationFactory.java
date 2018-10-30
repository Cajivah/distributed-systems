package com.cinema.api.factory;

import com.cinema.api.user.model.entity.HashedPassword;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class AuthenticationFactory {

     public static UserDetails createIssuer(String username, HashedPassword password) {
          return User.builder()
               .username(username)
               .password(password.getValue())
               .authorities(Collections.emptyList())
               .build();
     }
}
