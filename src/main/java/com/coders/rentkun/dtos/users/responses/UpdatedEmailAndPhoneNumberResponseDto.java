package com.coders.rentkun.dtos.users.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedEmailAndPhoneNumberResponseDto {
    private Long id;
    private String email;
    private String phoneNumber;
}
