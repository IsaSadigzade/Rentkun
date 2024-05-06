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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VehicleLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String url;
    private String downloadUrl;
    private String filePath;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

//    @UpdateTimestamp
//    @Temporal(TemporalType.DATE)
//    private LocalDate updatedAt;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_id")
//    @JsonIgnore
//    private Vehicle vehicle;

    public VehicleLogo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
