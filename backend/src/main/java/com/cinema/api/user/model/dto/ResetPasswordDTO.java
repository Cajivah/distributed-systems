package com.cinema.api.user.model.dto;

import com.cinema.api.util.annotation.matchingPasswords.MatchingPasswords;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordDTO {

     @NotBlank
     private String token;

     @Valid
     @MatchingPasswords
     private PasswordPairDTO passwordPairDTO;

}
