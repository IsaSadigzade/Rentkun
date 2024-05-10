package com.coders.rentkun.entities.vehicles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private String url;
    private String downloadUrl;
    private String filePath;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

//    @ElementCollection
//    private Set<String> filePaths;

//    @UpdateTimestamp
//    @Temporal(TemporalType.DATE)
//    private LocalDate updatedAt;

    @ManyToMany(mappedBy = "vehicleImages")
    private Set<Vehicle> vehicles;

    public VehicleImages(Long id, String name, String url, String downloadUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.downloadUrl = downloadUrl;
    }
}