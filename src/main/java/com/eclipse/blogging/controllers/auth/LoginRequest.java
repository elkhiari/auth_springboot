package com.eclipse.blogging.controllers.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
