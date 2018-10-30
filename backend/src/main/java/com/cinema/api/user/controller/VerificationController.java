package com.cinema.api.user.controller;

import com.cinema.api.user.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
@AllArgsConstructor
public class VerificationController {

     private final VerificationTokenService verificationTokenService;

     @PatchMapping
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void verifyRegistration(@RequestParam String token) {
          verificationTokenService.verifyUser(token);
     }
}
