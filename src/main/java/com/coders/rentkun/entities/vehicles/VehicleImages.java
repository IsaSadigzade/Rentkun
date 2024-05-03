package com.coders.rentkun.entities.vehicles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VehicleImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ElementCollection
    private Set<String> filePaths;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnore
    private Vehicle vehicle;
}
