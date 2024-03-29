package com.cinema.api.user.mapper;

import com.cinema.api.user.model.dto.ChangePasswordDTO;
import com.cinema.api.user.model.dto.PasswordPairDTO;
import com.cinema.api.user.model.dto.ResetPasswordDTO;
import com.cinema.api.user.model.dto.UserDetailsDTO;
import com.cinema.api.user.model.dto.UserRegistrationDTO;
import com.cinema.api.user.model.entity.HashedPassword;
import com.cinema.api.user.model.entity.UserCredentials;
import com.cinema.api.util.mapper.DateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = {DateMapper.class, RoleMapper.class})
public abstract class UserMapper {

     PasswordEncoder passwordEncoder;

     @Autowired
     public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
          this.passwordEncoder = passwordEncoder;
     }

     @Mapping(target = "password", source = "passwordPair")
     @Mapping(target = "id", ignore = true)
     @Mapping(target = "created", ignore = true)
     @Mapping(target = "modified", ignore = true)
     @Mapping(target = "verified", ignore = true)
     @Mapping(target = "logins", ignore = true)
     @Mapping(target = "locked", ignore = true)
     @Mapping(target = "roles", ignore = true)
     public abstract UserCredentials toUser(UserRegistrationDTO userRegistrationDTO);

     public abstract UserDetailsDTO toUserDetailsDTO(UserCredentials userCredentials);

     public UserCredentials updatePassword(ChangePasswordDTO changePasswordDTO,
                                           UserCredentials user) {
          return updatePassword(changePasswordDTO.getPasswordPairDTO(), user);
     }

     private UserCredentials updatePassword(PasswordPairDTO passwordPairDTO,
                                            UserCredentials user) {
          final HashedPassword hashedPassword = toHashedPassword(passwordPairDTO);
          user.setPassword(hashedPassword);
          return user;
     }

     public UserCredentials updatePassword(ResetPasswordDTO resetPasswordDTO,
                                           UserCredentials user) {
          return updatePassword(resetPasswordDTO.getPasswordPairDTO(), user);
     }

     public HashedPassword toHashedPassword(PasswordPairDTO passwordPairDTO) {
          return toHashedPassword(passwordPairDTO.getPassword());
     }

     public HashedPassword toHashedPassword(String password) {
          return HashedPassword.of(password, passwordEncoder);
     }
}
