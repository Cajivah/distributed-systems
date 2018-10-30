package com.cinema.api.user.service;

import com.cinema.api.user.exception.Fault;
import com.cinema.api.user.model.entity.Role;
import com.cinema.api.user.model.entity.UserCredentials;
import com.cinema.api.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.cinema.api.user.util.AvailableUserRoles.ROLE_USER;

@Service
@AllArgsConstructor
public class RoleService {

     private final RoleRepository roleRepository;

     public UserCredentials assignDefaultRole(UserCredentials userCredentials) {
          final Role defaultRole = findOneByName(ROLE_USER);
          userCredentials.setRoles(Collections.singleton(defaultRole));
          return userCredentials;
     }

     private Role findOneByName(String name) {
          return roleRepository
               .findOneByName(name)
               .orElseThrow(Fault::new);
     }
}
