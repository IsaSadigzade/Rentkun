package com.coders.rentkun.dtos.users.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImageUploadDto {
    private Long userId;
    private MultipartFile file;
}
