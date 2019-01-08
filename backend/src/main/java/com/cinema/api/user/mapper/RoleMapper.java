package com.cinema.api.user.mapper;

import com.cinema.api.user.model.dto.RoleDTO;
import com.cinema.api.user.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

     RoleDTO toRoleDTO(Role role);
}
