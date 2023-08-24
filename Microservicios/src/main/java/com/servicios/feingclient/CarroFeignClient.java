package com.servicios.feingclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.servicios.modelo.Carro;

//@FeignClient(name = "${app.feign.config.name}", url = "${app.feign.config.url}")
@FeignClient(name = "Microservicios-carro", url = "http://localhost:8082")
public interface CarroFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/carro", consumes = "application/json")
	public Carro save(@RequestBody Carro carro);
    
	@RequestMapping(method = RequestMethod.GET, value = "/carro/{usuarioId}")
	 public List<Carro> obtenerCarros(@PathVariable ("usuarioId") Long Id);
}
