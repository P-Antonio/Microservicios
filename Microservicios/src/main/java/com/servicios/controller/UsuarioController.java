package com.servicios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.modelo.Carro;
import com.servicios.modelo.Moto;
import com.servicios.service.Servicios;
import com.servicios.user.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private Servicios servicios;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuario = servicios.getAll();
		if(usuario.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/{d}")
	public ResponseEntity obtenerUsuario(@PathVariable Long id){
		Usuario usuario = servicios.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity guardarUsuario(@RequestBody  @Valid Usuario usuario){
		Usuario nuevoUsuario = servicios.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/carro/{usuarioId}")
	public ResponseEntity<List<Carro>> obtenerCarro(@PathVariable ("usuarioId") Long id){
		Usuario usuario = servicios.getUsuarioById(id);
		if(usuario == null){
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = servicios.getCarro(id);
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/moto/{usuarioId}")
	public ResponseEntity<List<Moto>> obtenerMoto(@PathVariable ("usuarioId") Long id){
		Usuario usuario = servicios.getUsuarioById(id);
		if(usuario == null){
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = servicios.getMoto(id);
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable ("usuarioId") Long usuarioId, @RequestBody Carro carro){
		Carro nuevoCarro = servicios.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	/*@GetMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> mostrarCarrosUsuario(@PathVariable ("usuarioId") Long id){
		Carro carro = servicios.getCarro(id);
		return ResponseEntity.ok(carro);
	}*/
	
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable ("usuarioId") Long usuarioId, @RequestBody Moto moto){
		Moto nuevaMoto = servicios.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	/*@GetMapping("/moto/{usuarioId}")
	public ResponseEntity<List<Moto>> mostrarMotosUsuario(@PathVariable ("usuarioId") Long id){
		List<Moto> moto = servicios.obtenerMotos(id);
		return ResponseEntity.ok(moto);
	}*/
	
	@GetMapping("/todo/{usuarioId}")
	public ResponseEntity<Map<String, Object>> obtenerTodo(@PathVariable ("usuarioId") Long usuarioId){
		Map<String, Object> resultado = servicios.obtenerUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}
}
