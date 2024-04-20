package com.coders.rentkun.dtos.users.responses;

import com.coders.rentkun.enums.users.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private String cityAndZipCode;
    private String location;
    private LocalDate dateOfBirth;
//    private byte[] image;
}
