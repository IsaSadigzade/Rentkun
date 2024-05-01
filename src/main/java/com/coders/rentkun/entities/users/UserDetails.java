package com.coders.rentkun.entities.users;

import com.coders.rentkun.enums.users.Gender;
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
public class UserDetails {
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
