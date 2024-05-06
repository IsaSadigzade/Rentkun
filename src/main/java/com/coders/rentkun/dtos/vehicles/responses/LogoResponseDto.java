package com.coders.rentkun.dtos.vehicles.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoResponseDto {
    private Long id;
    private String name;
    private String url;
    private String downloadUrl;
}
