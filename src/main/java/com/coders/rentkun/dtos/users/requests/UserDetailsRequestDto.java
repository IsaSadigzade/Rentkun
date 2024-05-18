package com.coders.rentkun.dtos.users.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String cityAndZipCode;
    private String location;
    private LocalDate birthDate;
}
