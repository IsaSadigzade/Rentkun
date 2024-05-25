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
    private String gender;
    private String cityAndZipCode;
    private String location;
    private LocalDate dateOfBirth;
//    private byte[] image;
    private String token;

    public CurrentUserResponseDto(Long id, String firstName, String lastName, String phoneNumber, String email, String gender, String cityAndZipCode, String location, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.cityAndZipCode = cityAndZipCode;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
    }
}
