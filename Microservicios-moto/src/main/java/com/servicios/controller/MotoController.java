package com.servicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.entidades.Moto;
import com.servicios.service.MotoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> moto = motoService.getAll();
		if(moto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(moto);
	}
	
	@GetMapping("/{d}")
	public ResponseEntity obtenerMoto(@PathVariable Long id){
		Moto moto = motoService.getMotoById(id);
		if(moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity guardarMoto(@PathVariable  @Validated Moto moto){
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotoByUsuario(@PathVariable Long usuarioId){
		List<Moto> moto = motoService.byUsuarioId(usuarioId);
		if(moto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(moto);
	}
}
