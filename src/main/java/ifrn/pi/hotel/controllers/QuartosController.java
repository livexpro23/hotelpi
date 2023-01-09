package ifrn.pi.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuartosController {

	@RequestMapping("/quartos")
	public String quartosPage() {
		return "Quartos/quartos";
	}
}
