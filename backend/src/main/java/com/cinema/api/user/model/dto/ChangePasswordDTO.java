package com.cinema.api.user.model.dto;

import com.cinema.api.util.annotation.matchingPasswords.MatchingPasswords;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ChangePasswordDTO {

     @NotNull
     private String currentPassword;

     @MatchingPasswords
     private PasswordPairDTO passwordPairDTO;
}
