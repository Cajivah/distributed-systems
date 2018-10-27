package com.cinema.api.util;

import com.cinema.api.factory.AuthenticationFactory;
import com.cinema.api.user.model.entity.HashedPassword;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SecurityTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        var testUserPassword =
                HashedPassword.ofHashed("$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.");
        final UserDetails testUser = AuthenticationFactory.createIssuer("TestUser", testUserPassword);

        return new InMemoryUserDetailsManager(Arrays.asList(
                testUser
        ));
    }
}
