package it.epicode.da.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.da.enums.RoleType;
import it.epicode.da.model.Role;




public interface RoleRepo  extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleType(RoleType r);
}
