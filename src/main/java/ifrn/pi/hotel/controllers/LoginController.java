package ifrn.pi.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginPage() {
		return "Login/login";
	}
	
	@GetMapping("/acessoNegado")
	public String negadoPage() {
		return "Aviso/acessoNegado";
	}
}
