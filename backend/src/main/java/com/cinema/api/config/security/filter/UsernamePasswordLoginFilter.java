package com.cinema.api.config.security.filter;

import com.cinema.api.config.security.mapper.WebSecurityMapper;
import com.cinema.api.config.security.util.HttpServletResponseDecorator;
import com.cinema.api.user.model.dto.LoginCompleteDTO;
import com.cinema.api.user.model.dto.UserDetailsDTO;
import com.cinema.api.user.model.event.OnLoginCompleteEvent;
import com.cinema.api.user.service.UserCredentialsService;
import com.cinema.api.util.exception.model.dto.ErrorDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UsernamePasswordLoginFilter extends UsernamePasswordAuthenticationFilter {

     private final WebSecurityMapper webSecurityMapper;
     private final UserCredentialsService userCredentialsService;

     public UsernamePasswordLoginFilter(AuthenticationManager authenticationManager,
                                        ApplicationEventPublisher eventPublisher,
                                        WebSecurityMapper webSecurityMapper,
                                        UserCredentialsService userCredentialsService) {
          this.setAuthenticationManager(authenticationManager);
          this.setApplicationEventPublisher(eventPublisher);
          this.webSecurityMapper = webSecurityMapper;
          this.userCredentialsService = userCredentialsService;
     }

     @Override
     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                             Authentication authentication) throws IOException {
          SecurityContextHolder.getContext().setAuthentication(authentication);
          setSuccessfulResponse(
               new HttpServletResponseDecorator(response));
          fireSuccessfulLoginEvent(request, authentication);
     }

     private void setSuccessfulResponse(HttpServletResponseDecorator response) throws IOException {
          final String userBody = getUserBody();
          response.setJSONBody(userBody);
          response.setStatusOK();
     }

     private String getUserBody() throws IOException {
          final UserDetailsDTO user = userCredentialsService.getAuthenticatedUserDetails();
          return webSecurityMapper.toJSON(user);
     }

     @Override
     protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                               AuthenticationException authException) throws IOException {
          SecurityContextHolder.clearContext();
          setUnsuccessfulResponse(response, authException);
     }

     private void setUnsuccessfulResponse(HttpServletResponse response, AuthenticationException authException) throws IOException {
          final ErrorDTO errorDTO = webSecurityMapper.toErrorDTO(authException);
          response.getWriter().print(webSecurityMapper.toJSON(errorDTO));
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     }

     private void fireSuccessfulLoginEvent(HttpServletRequest request, Authentication authentication) {
          final LoginCompleteDTO loginCompleteDTO =
               webSecurityMapper.toLoginCompleteDTO(request, authentication);
          eventPublisher.publishEvent(new OnLoginCompleteEvent(this.getClass(), loginCompleteDTO));
     }
}
