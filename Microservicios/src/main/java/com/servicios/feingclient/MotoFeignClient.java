 package com.servicios.feingclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.servicios.modelo.Moto;

@FeignClient(name= "Microservicios-moto", url = "http://localhost:8083")
public interface MotoFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/moto", consumes = "application/json")
	public Moto save(@RequestBody Moto moto);
	
	 @RequestMapping(method = RequestMethod.GET, value = "/moto/{usuarioId}")
	 public List<Moto> obtenerMotos(@PathVariable ("usuarioId") Long Id);
}
