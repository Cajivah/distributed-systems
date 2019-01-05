package com.cinema.api.config.security;

import com.cinema.api.config.security.filter.UsernamePasswordLoginFilter;
import com.cinema.api.config.security.logout.NopLogoutSuccessHandler;
import com.cinema.api.config.security.mapper.WebSecurityMapper;
import com.cinema.api.config.security.session.FailureHandlingAuthenticationEntryPoint;
import com.cinema.api.user.service.UserCredentialsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import static com.cinema.api.config.security.util.SecurityConstants.ANY_PATH;
import static com.cinema.api.config.security.util.SecurityConstants.AUTH_WHITELIST;
import static com.cinema.api.user.util.AvailableUserRoles.ROLE_ADMIN_SHORT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

     private final UserCredentialsService userDetailsService;
     private final WebSecurityMapper webSecurityMapper;
     private final PasswordEncoder passwordEncoder;
     private final ApplicationEventPublisher eventPublisher;

     private UsernamePasswordLoginFilter usernamePasswordLoginFilter() throws Exception {
          return new UsernamePasswordLoginFilter(
               authenticationManager(),
               eventPublisher,
               webSecurityMapper,
               userDetailsService);
     }

     private AuthenticationEntryPoint authenticationFailureHandler() {
          return new FailureHandlingAuthenticationEntryPoint(webSecurityMapper);
     }

     // @formatter:off
     @Override
     protected void configure(HttpSecurity http) throws Exception {
          http
               .csrf().disable()
               .cors()
               .and()
                    .exceptionHandling()
                         .authenticationEntryPoint(authenticationFailureHandler())
               .and()
                    .authorizeRequests()
                         .antMatchers(AUTH_WHITELIST).permitAll()
                         .antMatchers(HttpMethod.OPTIONS, ANY_PATH).permitAll()
                         .antMatchers(HttpMethod.POST, "/accounts").permitAll()
                         .antMatchers(HttpMethod.POST, "/accounts/reset-password").permitAll()
                         .antMatchers(HttpMethod.PATCH, "/accounts/reset-password").permitAll()
                         .antMatchers(HttpMethod.GET, "/cinema").permitAll()
                         .antMatchers(HttpMethod.POST, "/seances/*/bookings").permitAll()
                         .antMatchers(HttpMethod.GET, "/seances/*").permitAll()
                         .antMatchers(
                              "/",
                              "/*/lock")
                              .hasRole(ROLE_ADMIN_SHORT)
                         .anyRequest()
                              .authenticated()
               .and()
                    .addFilter(usernamePasswordLoginFilter())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
               .and()
                    .logout()
                         .logoutSuccessHandler(new NopLogoutSuccessHandler())
          ;
     }
     // @formatter:on

     @Override
     public void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
     }
}
