package com.cinema.api.user.service;

import com.cinema.api.config.security.mapper.WebSecurityMapper;
import com.cinema.api.user.mapper.LoginMapper;
import com.cinema.api.user.model.dto.LoginCompleteDTO;
import com.cinema.api.user.model.entity.UserCredentials;
import com.cinema.api.user.model.entity.UserLogin;
import com.cinema.api.user.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginService {

     private final UserCredentialsService userCredentialsService;
     private final UserLoginRepository userLoginRepository;
     private final LoginMapper loginMapper;
     private final WebSecurityMapper webSecurityMapper;

     public void updateLastLoginDate(LoginCompleteDTO loginCompleteDTO) {
          final User user =
               webSecurityMapper.toPrincipal(loginCompleteDTO.getAuthentication());
          final UserCredentials userCredentials =
               userCredentialsService.findByUsername(user.getUsername());
          final UserLogin userLogin = loginMapper.toUserLogin(loginCompleteDTO, userCredentials);
          userLoginRepository.save(userLogin);
     }
}
