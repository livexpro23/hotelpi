package ifrn.pi.hotel.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ifrn.pi.hotel.models.RoleModel;
import ifrn.pi.hotel.models.UserModel;
import ifrn.pi.hotel.repositories.RoleRepository;
import ifrn.pi.hotel.repositories.UserRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/cadastro")
	public String cadastroPage() {
		return "Login/cadastroForm";
	}
	
	@PostMapping("/cadastro")
	public String salvar(UserModel userModel) {
		
		ArrayList<RoleModel> roles = new ArrayList<RoleModel>();
		RoleModel role = roleRepository.findByRoleName("ROLE_USER");
		roles.add(role);
		
		userModel.setRoles(roles);
		userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
		
		userRepository.save(userModel);
		
		return "redirect:/login";
	}
}
