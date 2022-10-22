package com.fynjy.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    private String username;
    private String email;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean enabled = true;
}
