package com.servicios.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

	private String marca;
	private String modelo;
	private Long usuarioId;
}
