package com.cinema.api.user.mapper;

import com.cinema.api.user.model.dto.VerificationMailDTO;
import com.cinema.api.user.model.entity.VerificationToken;
import com.cinema.api.user.model.event.OnRegistrationCompleteEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerificationMapper {

     @Mapping(target = "username", source = "token.user.username")
     @Mapping(target = "address", source = "token.user.email")
     @Mapping(target = "token", source = "token.token")
     @Mapping(target = "locale", source = "event.locale")
     VerificationMailDTO verificationMailDTO(VerificationToken token,
                                             OnRegistrationCompleteEvent event);
}
