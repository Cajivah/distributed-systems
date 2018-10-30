package com.cinema.api.user.service;

import com.cinema.api.user.exception.VerificationException;
import com.cinema.api.user.model.dto.ResetPasswordDTO;
import com.cinema.api.user.model.dto.UserDetailsDTO;
import com.cinema.api.user.model.entity.UserCredentials;
import com.cinema.api.user.model.entity.VerificationToken;
import com.cinema.api.user.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cinema.api.user.util.VerificationTokenUtils.getEarliestValidCreationDate;

@Service
@AllArgsConstructor
public class VerificationTokenService {

     private final UserCredentialsService userCredentialsService;
     private final VerificationTokenRepository verificationTokenRepository;

     public VerificationToken createNewToken(UserCredentials user) {
          final VerificationToken token = buildToken(user);
          return verificationTokenRepository.save(token);
     }

     private VerificationToken buildToken(UserCredentials userCredentials) {
          final String uuid = UUID.randomUUID().toString();
          return VerificationToken.builder()
                                  .token(uuid)
                                  .user(userCredentials)
                                  .build();
     }

     public VerificationToken createNewToken(UserDetailsDTO user) {
          final UserCredentials userCredentials =
                  userCredentialsService.findByUsername(user.getUsername());
          final VerificationToken token = buildToken(userCredentials);
          return verificationTokenRepository.save(token);
     }

     public void verifyUser(String token) {
          final VerificationToken verificationToken = findVerificationByToken(token);
          persistVerification(verificationToken);
     }

     private VerificationToken findVerificationByToken(String token) {
          return verificationTokenRepository.findOneByTokenAndCreatedAfter(
               token,
               getEarliestValidCreationDate())
               .orElseThrow(VerificationException::of);
     }

     private void persistVerification(VerificationToken verificationToken) {
          userCredentialsService.verifyUser(verificationToken.getUser());
          verificationTokenRepository.delete(verificationToken);
     }

     public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
          VerificationToken verificationToken = findVerificationByToken(resetPasswordDTO.getToken());
          UserCredentials user = verificationToken.getUser();
          userCredentialsService.resetPassword(resetPasswordDTO, user);
          verificationTokenRepository.delete(verificationToken);
     }
}
