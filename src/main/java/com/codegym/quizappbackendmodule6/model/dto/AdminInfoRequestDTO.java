package com.codegym.quizappbackendmodule6.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class AdminInfoRequestDTO {
    private Long id;
    private String name;
    private String email;
    private String currentPassword;
    private String newPassword;
    private MultipartFile avatar;

}
