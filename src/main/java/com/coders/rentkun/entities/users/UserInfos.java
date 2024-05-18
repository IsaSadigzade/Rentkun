package com.coders.rentkun.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
//    @Column(unique = true)
//    private String email;
    @Column(unique = true)
    private String phoneNumber;
//    @Enumerated(EnumType.STRING)
    private String gender;
    private String cityAndZipCode;
    private String location;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;

    @OneToOne(mappedBy = "userInfos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public UserInfos(String firstName, String lastName, String phoneNumber, String gender, String cityAndZipCode, String location, LocalDate dateOfBirth, LocalDate createdAt, LocalDate updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.cityAndZipCode = cityAndZipCode;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
