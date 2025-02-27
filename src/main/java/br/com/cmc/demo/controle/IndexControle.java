package br.com.cmc.demo.controle;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cmc.demo.modelo.Feedback;
import br.com.cmc.demo.modelo.SoftBox;
import br.com.cmc.demo.modelo.Usuario;
import br.com.cmc.demo.repositorio.UsuarioRepositorio;


@Controller
public class IndexControle {
	
	@Autowired
	UsuarioRepositorio usRepositorio;

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
        
		model.addAttribute("listaUS", Uss);
		return "/auth/admin/lista-usuario";
	}
}
