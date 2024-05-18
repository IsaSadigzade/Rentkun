package com.coders.rentkun.dtos.users.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String email;
    private String password;
    private String confirmPassword;
    private UserDetailsRequestDto userDetails;
}