package br.com.cmc.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
