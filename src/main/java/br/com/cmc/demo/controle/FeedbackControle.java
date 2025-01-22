package br.com.cmc.demo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cmc.demo.modelo.Feedback;
import br.com.cmc.demo.repositorio.FeedbackRepositorio;


@Controller
@RequestMapping("/feedback")
public class FeedbackControle {
	
	@Autowired
	FeedbackRepositorio fbRepositorio;
	
	@GetMapping("/novo")
    public String softbox(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("novoFB", feedback);
        return "/auth/user/novo-feedback";
    }
	
	@PostMapping("/salvar")
    public String salvarFB(@ModelAttribute("novoFB") @Validated Feedback fB, BindingResult result,
                           RedirectAttributes attributes, Model model) {
        // Se houver erros de validação, redirecione de volta ao formulário
        if (result.hasErrors()) {
            return "/auth/user/novo-feedback"; // Retorna ao formulário com erros
        }

        // Se passar nas validações, salva no repositório
        fbRepositorio.save(fB);
        attributes.addFlashAttribute("mensagemFB", "Obrigado pelo feedback");
        return "redirect:/feedback/novo"; // Redireciona após sucesso
    }
	
	@GetMapping("/listar")
	public String listarFB(Model model) {
		List<Feedback> fBs = fbRepositorio.findAll();
        
		model.addAttribute("listaFB", fBs);
		return "/auth/admin/lista-feedback";
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarSB(@PathVariable("id") long id, Model model) {
		Feedback fB = fbRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		fbRepositorio.delete(fB);
		return "redirect:/feedback/listar";
	}
}
