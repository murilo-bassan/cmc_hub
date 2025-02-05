package br.com.cmc.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class SoftBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A ideia deve ser informada")
    @Size(min = 3, message = "A ideia deve conter pelo menos 3 caracteres")
    private String descricaoSB;

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 3, message = "O nome da empresa deve conter pelo menos 3 caracteres")
    private String nomeEmpresa;

    @NotBlank(message = "Não podemos contactá-lo sem o telefone")
    @Size(min = 8, message = "O Telefone inserido é curto demais")
    @Pattern(regexp = "^[0-9\\+\\-\\(\\)\\s]+$", message = "O telefone deve conter apenas números e sinais")
    private String telefoneEmpresa;
    
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoSB() {
		return descricaoSB;
	}

	public void setDescricaoSB(String descricaoSB) {
		this.descricaoSB = descricaoSB;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getTelefoneEmpresa() {
		return telefoneEmpresa;
	}

	public void setTelefoneEmpresa(String telefoneEmpresa) {
		this.telefoneEmpresa = telefoneEmpresa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
