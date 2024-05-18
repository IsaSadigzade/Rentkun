package com.coders.rentkun.dtos.users.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImagesResponseDto {
    private Long id;
    private String name;
    private String url;
    private String downloadUrl;
}
