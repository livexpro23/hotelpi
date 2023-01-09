package ifrn.pi.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.hotel.models.Reserva;
import ifrn.pi.hotel.models.Quartos;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	List<Reserva> findByQuartos(Quartos quartos);

}
