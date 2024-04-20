package com.coders.rentkun.dtos.users.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailAndPhoneNumberUpdateRequestDto {
    private String email;
    private String phoneNumber;
}
