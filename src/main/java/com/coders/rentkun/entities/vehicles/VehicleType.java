package com.coders.rentkun.entities.vehicles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "vehicleType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Vehicle vehicle;
}
