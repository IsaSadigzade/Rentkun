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
public class VehicleFuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "vehicleFuelType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;
}
