package com.coders.rentkun.dtos.users.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordUpdateRequestDto {
    private String oldPassword;
    private String newPassword;
}
