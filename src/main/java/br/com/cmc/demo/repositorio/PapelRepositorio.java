package br.com.cmc.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cmc.demo.modelo.SoftBox;

public interface PapelRepositorio extends JpaRepository <SoftBox, Long>{

}
