package ifrn.pi.hotel.models;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_reservas")
public class Reserva {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConvidado;
	@NotBlank
	private String nome;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private LocalTime horario;
	
	@ManyToOne
	private Quartos quartos;

	public Long getId() {
		return idConvidado;
	}

	public void setId(Long idConvidado) {
		this.idConvidado = idConvidado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public Quartos getEvento() {
		return quartos;
	}

	public void setEvento(Quartos quartos) {
		this.quartos = quartos;
	}

	@Override
	public String toString() {
		return "Convidado [idConvidado=" + idConvidado + ", nome=" + nome + ", data=" + data + ", horario=" + horario + ", evento="
				+ quartos + "]";
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
}
