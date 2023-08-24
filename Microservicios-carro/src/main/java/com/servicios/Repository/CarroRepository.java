package com.servicios.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicios.entidades.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findByUsuarioId (Long UsuarioId);
}
