package com.cinema.api.user.repository;

import com.cinema.api.user.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

     Optional<VerificationToken> findOneByTokenAndCreatedAfter(String token, LocalDateTime localDateTime);
}
