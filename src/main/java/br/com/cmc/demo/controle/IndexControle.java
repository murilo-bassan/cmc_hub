package br.com.cmc.demo.controle;

import java.util.List;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cmc.demo.modelo.Usuario;
import br.com.cmc.demo.repositorio.PapelRepositorio;
import br.com.cmc.demo.repositorio.UsuarioRepositorio;


@Controller
public class IndexControle {
	
	@Autowired
	UsuarioRepositorio usRepositorio;
	
	@Autowired
	PapelRepositorio papelRepositorio;

	@GetMapping("/")
    public String pagInicial(){	
	
		return "/auth/user/home";
    }
	
	@GetMapping("/login")
    public String Login(Model model){	
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
        model.addAttribute("novoUser", usuario);	
		return "/auth/login";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute("novoUser") @Validated Usuario User, BindingResult result,
                           RedirectAttributes attributes, Model model) {
        // Se houver erros de validação, redirecione de volta ao formulário
        if (result.hasErrors()) {
            return "/auth/login"; // Retorna ao formulário com erros
        }

        // Se passar nas validações, salva no repositório
        usRepositorio.save(User);
        attributes.addFlashAttribute("mensagemLogin", "Usuário cadastrado com Sucesso!");
        return "redirect:/login"; // Redireciona após sucesso
    }
	
	@GetMapping("/listar")
	public String listarFB(Model model) {
		List<Usuario> Uss = usRepositorio.findAll();
        
		model.addAttribute("listaUSR", Uss);
		return "/auth/admin/lista-usuario";
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarSB(@PathVariable("id") long id, Model model) {
		Usuario us = usRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		usRepositorio.delete(us);
		return "redirect:/listar";
	}
	
	@GetMapping("/editarPapel/{id}")
	public String selecionarPapel(@PathVariable("id") long id, Model model) {
	    Usuario usuario = usRepositorio.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Usuário inválido: " + id));

	    model.addAttribute("usuario", usuario);
	    model.addAttribute("listaPapeis", papelRepositorio.findAll());

	    return "/auth/admin/editar-papel";
	}

}
