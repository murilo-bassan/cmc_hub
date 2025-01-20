package br.com.cmc.demo.controle;

import br.com.cmc.demo.modelo.SoftBox;
import br.com.cmc.demo.repositorio.SoftBoxRepositorio;
import br.com.cmc.demo.servico.SoftboxServico;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/softbox")
public class SoftboxControle {

    @Autowired
    private SoftBoxRepositorio sbRepositorio; // Injeção do repositório
    

    @GetMapping("/novo")
    public String softbox(Model model) {
        SoftBox softbox = new SoftBox();
        softbox.setStatus("nao-lida");
        model.addAttribute("novoSB", softbox);
        return "/auth/user/novo-softbox";
    }

    @PostMapping("/salvar")
    public String salvarSB(@ModelAttribute("novoSB") @Validated SoftBox sB, BindingResult result,
                           RedirectAttributes attributes, Model model) {
        // Se houver erros de validação, redirecione de volta ao formulário
        if (result.hasErrors()) {
            return "/auth/user/novo-softbox"; // Retorna ao formulário com erros
        }

        // Se passar nas validações, salva no repositório
        sbRepositorio.save(sB);
        attributes.addFlashAttribute("mensagemSB", "Ideia enviada para análise! Aguarde o retorno");
        return "redirect:/softbox/novo"; // Redireciona após sucesso
    }
    
    @GetMapping("/apagar/{id}")
	public String apagarSB(@PathVariable("id") long id, Model model) {
		SoftBox sB = sbRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		sbRepositorio.delete(sB);
		return "redirect:/softbox/listar";
	}
    
    @PostMapping("/atualizar-status")
    public String atualizarStatus(@RequestParam Long id, @RequestParam String status) {
        SoftBox softBox = sbRepositorio.findById(id).orElse(null);  // Busca a softbox pelo ID
        if (softBox != null) {
            softBox.setStatus(status);  // Atualiza o status
            sbRepositorio.save(softBox);  // Salva a softbox no banco de dados
        }
        return "redirect:/softbox/listar";  // Redireciona para a lista de softboxes ou página desejada
    }
    
    @GetMapping("/listar")
	public String listarSB(Model model) {
		List<SoftBox> sBs = sbRepositorio.findAll();
		model.addAttribute("listaSB", sBs);
		return "/auth/admin/lista-softbox";
	}
}
