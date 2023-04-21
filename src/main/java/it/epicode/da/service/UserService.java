package it.epicode.da.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.epicode.da.model.Role;
import it.epicode.da.model.User;
import it.epicode.da.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
@Service
public class UserService {
	@Autowired
	UserRepo uRepo;
	@Autowired
	@Qualifier("FakeUser")
	private ObjectProvider<User> fakeUtenteProvider;
	@Autowired
	@Qualifier("ParamsUser")
	private ObjectProvider<User> paramUtenteProvider;
	@Autowired
	@Qualifier("FakeRole")
	private ObjectProvider<Role> fakeRoleProvider;
	@Deprecated
	public void createAndSaveFakeUtente(int n) {
		for (int i = 0; i < n; i++) {
			
			saveUser(fakeUtenteProvider.getObject());
		}
	}
	public void saveUser(User u) {
		uRepo.save(u);
	}

	public void updateUtente(User u) {
		if (!uRepo.existsById(u.getId_user())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.save(u);
		}
	}
	public User findUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			return uRepo.findById(id).get();
		}
	}

	public List<User> findAllUtente() {
		return (List<User>) uRepo.findAll();
	}

	public void removeUtente(User u) {
		if (!uRepo.existsById(u.getId_user())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.delete(u);
		}
	}

	public String removeUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.deleteById(id);
			return "Utente eliminato";
		}
	}
}
