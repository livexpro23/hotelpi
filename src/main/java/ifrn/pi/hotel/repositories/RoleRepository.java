package ifrn.pi.hotel.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifrn.pi.hotel.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long>{

	RoleModel findByRoleName(String username);
	
	Iterable<RoleModel> findAllByOrderByRoleNameAsc();
}
