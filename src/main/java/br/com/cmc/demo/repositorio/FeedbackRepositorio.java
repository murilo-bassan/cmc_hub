package br.com.cmc.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cmc.demo.modelo.Feedback;

public interface FeedbackRepositorio extends JpaRepository <Feedback, Long>{

}
