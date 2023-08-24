package com.servicios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.entidades.Carro;
import com.servicios.service.CarroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<Carro>> listarCarros(){
		List<Carro> carro = carroService.getAll();
		if(carro.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity obtenerCarro(@PathVariable Long id){
		Carro carro= carroService.getCarroById(id);
		if(carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity guardarCarro(@RequestBody  @Valid Carro carro){
		Carro nuevoCarro = carroService.save(carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarroByUsuario(@PathVariable Long usuarioId){
		List<Carro> carro = carroService.byUsuarioId(usuarioId);
		if(carro.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carro);
	}
}
