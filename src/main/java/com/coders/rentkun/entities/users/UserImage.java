package com.coders.rentkun.entities.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String filePath;

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
