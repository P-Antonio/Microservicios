package com.servicios.user;

import jakarta.validation.constraints.NotBlank;

public record guardarUsuario(
		 @NotBlank
		 String nombre,
		 @NotBlank
		 String email) {

}
