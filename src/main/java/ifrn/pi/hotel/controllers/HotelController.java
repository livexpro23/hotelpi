package ifrn.pi.hotel.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pi.hotel.models.Reserva;
import ifrn.pi.hotel.models.Quartos;
import ifrn.pi.hotel.repositories.ReservaRepository;
import ifrn.pi.hotel.repositories.QuartoRepository;

@Controller
@RequestMapping("/hotelp")

public class HotelController {

	@Autowired
	private QuartoRepository er;
	@Autowired
	private ReservaRepository cr;

	@RequestMapping("/form")
	public String formPage(Quartos quartos) {
		return "Hotel/formQuartos";
	}

	@PostMapping("/add")
	private String adicionarQuarto(@Valid Quartos quartos, BindingResult result) {
		if (result.hasErrors()) {
			return formPage(quartos);
		}

		System.out.println(quartos);
		er.save(quartos);
		return "redirect:/hotelp";
	}

	@GetMapping
	private ModelAndView listarQuartos() {
		List<Quartos> quartos = er.findAll();
		ModelAndView mv = new ModelAndView("Hotel/lista");
		mv.addObject("quartos", quartos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView reservas(@PathVariable long id, Reserva reserva) {
		ModelAndView md = new ModelAndView();
		Optional<Quartos> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/hotelp");
			return md;
		}
		md.setViewName("Hotel/detalhes");
		Quartos quartos = opt.get();
		md.addObject("quarto", quartos);

		List<Reserva> reservas = cr.findByQuartos(quartos);
		md.addObject("reservas", reservas);

		return md;
	}

	@PostMapping("/{idQuarto}")
	public ModelAndView adicionarReserva(@PathVariable Long idQuarto, @Valid Reserva reserva, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView md = new ModelAndView();
		if (result.hasErrors()) {
			return reservas(idQuarto, reserva);
		}
		Optional<Quartos> opt = er.findById(idQuarto);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/hotelp");
			return md;
		}
		Quartos quartos = opt.get();
		reserva.setEvento(quartos);
		cr.save(reserva);
		md.setViewName("redirect:/hotelp/{idQuarto}");
		attributes.addFlashAttribute("mensagem", "Quarto Reservado!");
		return md;
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarQuarto(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Quartos> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/hotelp");
			return md;
		}
		Quartos quartos = opt.get();
		md.setViewName("Hotel/formQuarto");
		md.addObject("quartos", quartos);

		return md;
	}

	@GetMapping("/{id}/remover")
	public String removerQuarto(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Quartos> opt = er.findById(id);

		if (!opt.isEmpty()) {
			Quartos quartos = opt.get();
			List<Reserva> reservas = cr.findByQuartos(quartos);
			cr.deleteAll(reservas);
			er.delete(quartos);
			attributes.addFlashAttribute("mensagem", "Removido com sucesso!");
		}
		return "redirect:/hotelp";
	}

	@GetMapping("/{idQuarto}/convidados/{idConvidado}/deletar")
	public String deletarReserva(@PathVariable Long idQuarto, @PathVariable Long idConvidado,
			RedirectAttributes attributes) {

		Optional<Reserva> optConvidado = cr.findById(idConvidado);

		if (!optConvidado.isEmpty()) {
			Reserva reserva = optConvidado.get();
			cr.delete(reserva);
			attributes.addFlashAttribute("mensagem", " removido com sucesso!");
		}
		return "redirect:/hotelp/{idQuarto}";
	}
}