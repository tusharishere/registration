package com.api.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    @NotEmpty
    @Size(min=4,max = 50,message = "Name must be between 4 and 50 characters")
    private String name;

    @Email(message = "Email address is not valid !")
    private String email;

    @NotNull(message = "Mobile number cannot be null")
    @Size(min=10,max = 10, message = "Mobile number must be exactly 10 digits")
    private String mobile;

}