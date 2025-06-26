package br.com.cmc.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Feedback {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Avalie nosso serviço ou proposta")
	private float estrelas;
	
	@Size(min = 3, message = "O comentário deve conter pelo menos 3 caracteres")
	private String descricaoFeedback;

	public float getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(float estrelas) {
		this.estrelas = estrelas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoFeedback() {
		return descricaoFeedback;
	}

	public void setDescricaoFeedback(String descricaoFeedback) {
		this.descricaoFeedback = descricaoFeedback;
	}
}
