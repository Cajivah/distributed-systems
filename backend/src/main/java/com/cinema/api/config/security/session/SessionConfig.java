package com.cinema.api.config.security.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

     @Bean
     public LettuceConnectionFactory connectionFactory() {
          return new LettuceConnectionFactory();
     }

     @Bean
     public CookieSerializer cookieSerializer() {
          DefaultCookieSerializer serializer = new DefaultCookieSerializer();
          serializer.setCookieName("x-wa-p");
          serializer.setCookiePath("/");
          serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
          serializer.setUseHttpOnlyCookie(true);
          return serializer;
     }
}