package it.epicode.da.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.epicode.da.enums.RoleType;
import it.epicode.da.enums.StatoDispositivo;
import it.epicode.da.model.Role;
import it.epicode.da.model.User;

@Configuration
public class UserConfig {
	@Bean("ParamsUser")
	@Scope("prototype")
	public User paramsUser(String username, String name, String lastname, String email, String pas, Role e) {
		User u = new User();
		u.setName(name);
		u.setLastname(lastname);
		u.setEmail(email);
		u.setUsername(username);
		u.setPassword(pas);
		u.getRoles().add(e);
		return u;

	}

	@Bean("FakeRole")
	@Scope("prototype")
	public Role fakeRole() {
		Role r = new Role();
		int randN = (int) ((Math.random() * 3) + 1);
		switch (randN) {
		case 1: {
			r.setRoleType(RoleType.ROLE_ADMIN);
			break;
		}
		case 2: {
			r.setRoleType(RoleType.ROLE_MODERATOR);
			break;
		}
		case 3: {
			r.setRoleType(RoleType.ROLE_USER);
			break;
		}

		default:

			r.setRoleType(RoleType.ROLE_USER);
		}
		return r;
	}

	@Bean("FakeUser")
	@Scope("prototype")
	public User fakeUser() {
		Faker fake = Faker.instance(new Locale("it-It"));
		User u = new User();
		u.setName(fake.name().firstName());
		u.setLastname(fake.name().lastName());
		u.setEmail(u.getName() + "." + u.getLastname() + "@example.com");
		u.setUsername(u.getName() + "." + u.getLastname());
		u.setPassword("1234");
		

		return u;
	}
}
