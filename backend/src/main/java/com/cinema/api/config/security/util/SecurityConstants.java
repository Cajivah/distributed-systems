package com.cinema.api.config.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {

     public static final String[] AUTH_WHITELIST = {
          // -- swagger ui
          "/v2/api-docs",
          "/swagger-resources",
          "/swagger-resources/**",
          "/configuration/ui",
          "/configuration/security",
          "/swagger-ui.html",
          "/webjars/**",
          "/verification",
          "/app/**",
          "/js/**",
          "/css/**",
          "/img/**",
          "/fonts/**",
          "/favicon.ico",
          "/programme/**",
     };
     public static final String ANY_PATH = "/**";
}
