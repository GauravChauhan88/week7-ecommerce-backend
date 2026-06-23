package com.ecommerce.model.dto;

import com.ecommerce.model.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}