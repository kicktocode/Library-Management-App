package com.example.library_app.dto;

import com.example.library_app.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String userName;
    private Role role;
    private Long id;
}
