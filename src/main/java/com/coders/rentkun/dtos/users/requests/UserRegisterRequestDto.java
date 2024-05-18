package com.coders.rentkun.dtos.users.requests;

import com.coders.rentkun.enums.users.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String email;
    private String password;
    private String confirmPassword;
    private CreateUserDetailsDto userDetails;
}