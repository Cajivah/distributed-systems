package com.cinema.api.user.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class UserDetailsDTO implements Serializable {

     private String username;
     private String email;
     private Collection<RoleDTO> roles;

}
