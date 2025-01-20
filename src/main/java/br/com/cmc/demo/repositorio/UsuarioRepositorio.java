package br.com.cmc.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cmc.demo.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{

}
