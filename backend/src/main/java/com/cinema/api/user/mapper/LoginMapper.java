package com.cinema.api.user.mapper;

import com.cinema.api.user.model.dto.LoginCompleteDTO;
import com.cinema.api.user.model.entity.UserCredentials;
import com.cinema.api.user.model.entity.UserLogin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

     default UserLogin toUserLogin(LoginCompleteDTO loginCompleteDTO, UserCredentials userCredentials) {
          return UserLogin.builder()
               .userCredentials(userCredentials)
               .ip(loginCompleteDTO.getRequestDetails().getIp())
               .userAgent(loginCompleteDTO.getRequestDetails().getUserAgent())
               .build();
     }
}
