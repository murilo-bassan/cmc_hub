package br.com.cmc.demo.controle;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexControle {

	@GetMapping("/")
    public String pagInicial(){	
	
		return "/auth/user/home";
    }
}
