package com.eclipse.blogging.controllers.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Column(unique = true)
    @Email(message = "Invalid email address. Please enter a valid email address in the format example@example.com.\n")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Passwrod is mandatory")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and include at least one letter and one digit."
    )
    private String password;
}
