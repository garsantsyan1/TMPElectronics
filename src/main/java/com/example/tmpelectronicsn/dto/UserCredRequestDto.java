package com.example.tmpelectronicsn.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredRequestDto {


    @NotNull
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotNull
    @NotBlank(message = "Surname can't be empty")
    private String surname;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotEmpty
    @NotNull
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email is mandatory")
    private String email;

}
