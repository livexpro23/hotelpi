package ifrn.pi.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/inicio";
	}
	
	@GetMapping("/inicio")
	public String inicio() {
		return "Hotel/hotelinicio";
	}
}
