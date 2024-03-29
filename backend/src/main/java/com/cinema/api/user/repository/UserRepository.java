package com.cinema.api.user.repository;

import com.cinema.api.user.model.entity.UserCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Long> {

     Optional<UserCredentials> findByUsername(String username);

     Optional<UserCredentials> findByUsernameIgnoreCaseAndVerifiedTrue(String username);

     Optional<UserCredentials> findOneByEmail(String email);

     Page<UserCredentials> findAllByUsernameContainingOrEmailContainingAllIgnoreCase(String username, String email,
                                                            Pageable pageable);
}
