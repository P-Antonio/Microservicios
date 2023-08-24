package com.servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios.Repository.CarroRepository;
import com.servicios.entidades.Carro;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public List<Carro> getAll(){
		return carroRepository.findAll();
	}
	
	public Carro getCarroById(Long id) {
		return carroRepository.findById(id).orElse(null);
	}
	
	public Carro save (Carro carro) {
		Carro nuevoCarro = carroRepository.save(carro);
		return nuevoCarro;
	}
	
	public List<Carro> byUsuarioId(Long usuarioId){
		return carroRepository.findByUsuarioId(usuarioId);
	}
}
