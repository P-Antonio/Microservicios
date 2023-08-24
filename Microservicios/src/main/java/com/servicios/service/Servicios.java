package com.servicios.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.servicios.feingclient.CarroFeignClient;
import com.servicios.feingclient.MotoFeignClient;
import com.servicios.modelo.Carro;
import com.servicios.modelo.Moto;
import com.servicios.user.Usuario;
import com.servicios.user.UsuariosRepository;

@Service
public class Servicios {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	public List<Usuario> getAll(){
		return usuariosRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id) {
		return usuariosRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuariosRepository.save(usuario);
		return nuevoUsuario;
	}
	
	public List<Carro> getCarro(Long usuarioId){
		List<Carro> carros = restTemplate.getForObject("http://localhost:8082/carro/usuario/" + usuarioId, List.class);
		return carros;
	}
	
	public List<Moto> getMoto(Long usuarioId){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId, List.class);
		return motos;
	}
	
	public Carro saveCarro(Long usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;
	}
	public Moto saveMoto(Long usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}
	public List<Moto> obtenerMotos(Long id){
		List<Moto> motos = motoFeignClient.obtenerMotos(id);
		return motos;
	}
	
	public Map<String, Object> obtenerUsuarioAndVehiculos(Long usuarioId){
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuariosRepository.findById(usuarioId).orElse(null);
		if(usuario == null) {
			resultado.put("Mensaje", "el usuario no existe");
			return resultado;
		}
		resultado.put("Usuario", usuario);
		
		List<Carro> carros = carroFeignClient.obtenerCarros(usuarioId);
		if(carros.isEmpty()) {
			resultado.put("Mensaje", "el usuario no tiene carros");
		}
		else {
			resultado.put("Carros", carros);
		}
		
		List<Moto> motos = motoFeignClient.obtenerMotos(usuarioId);
		if(motos.isEmpty()) {
			resultado.put("mensaje", "El usuario no tiene motos");
		}
		else {
			resultado.put("Motos" , motos);
		}
		return resultado;
	}
}

