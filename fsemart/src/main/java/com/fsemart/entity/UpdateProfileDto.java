package com.fsemart.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileDto {

    @NotBlank
    private String username;

    @Email
    private String email;

    private String phone;

    //opsionale mund ta lej dhe null
    private String currentPassword;

    @Size(min = 4, message = "Passwordi minimumi 4 karaktere")
    private String newPassword;
}
