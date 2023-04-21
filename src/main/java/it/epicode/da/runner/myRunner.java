package it.epicode.da.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.epicode.da.enums.RoleType;
import it.epicode.da.model.Role;
import it.epicode.da.repository.RoleRepo;
import it.epicode.da.service.DispositivoService;
import it.epicode.da.service.UserService;


@Component
public class myRunner implements ApplicationRunner{
@Autowired
RoleRepo roleRepository;
@Autowired
UserService uSer;
@Autowired
DispositivoService disSer;
private Set<Role> adminRole;
private Set<Role> moderatorRole;
private Set<Role> userRole;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("run");
		//setRoleDefault();
		
		//disSer.createAndSaveFakeDispositovo(100);
		
	}
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleType(RoleType.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleType(RoleType.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleType(RoleType.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}

}
