package it.epicode.da.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.da.enums.StatoDispositivo;
import it.epicode.da.model.Dispositivo;
import it.epicode.da.model.User;
import it.epicode.da.repository.DispositivoRepo;
import it.epicode.da.repository.UserRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DispositivoService {
	@Autowired
	DispositivoRepo dRepo;
	@Autowired
	UserRepo uRepo;
	@Autowired
	UserService uService;
	@Autowired
	@Qualifier("FakeDispositivo")
	private ObjectProvider<Dispositivo> fakeDispositivoProvider;
	@Autowired
	@Qualifier("ParamDispositivo")
	private ObjectProvider<Dispositivo> paramDispositivoProvider;

	public void createAndSaveFakeDispositovo(int n) {
		for (int i = 0; i < n; i++) {
			saveDispositivo(fakeDispositivoProvider.getObject());
		}
	}

	public String collegaDispositivoAdUser(Long id_dis, Long id_user) {
		if (!dRepo.existsById(id_dis)) {
			throw new EntityNotFoundException("Dispositivo not exists!!!");
		}
		if (!uRepo.existsById(id_user)) {
			throw new EntityNotFoundException("User not exists!!!");
		}
		if (dRepo.findById(id_dis).get().getStatoDispositivo() != StatoDispositivo.DISPONIBILE) {
			throw new EntityNotFoundException("dispositivo non disponibile!!!");
		}
		User u = uRepo.findById(id_user).get();
		Dispositivo d = dRepo.findById(id_dis).get();
		System.out.println("aggiungo dis");
		System.out.println(u.getDispositivi() + " " + u.getName());
		u.getDispositivi().forEach(dis -> System.out.println(dis));
		u.addDipositivo(d);
		d.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
		d.setUser(u);
		uRepo.save(u);
		dRepo.save(d);
		return "Dispositivo collegato con sucesso";

	}

	public Dispositivo saveDispositivo(Dispositivo u) {
		dRepo.save(u);
		return u;
	}

	public Dispositivo updateDispositivo(Dispositivo u) {
		if (!dRepo.existsById(u.getId_dipositivo())) {
			throw new EntityNotFoundException("Dispositivo not exists!!!");
		} else {
			dRepo.save(u);
			return u;
		}
	}

	public Dispositivo findDispositivoById(Long id) {
		if (!dRepo.existsById(id)) {
			throw new EntityNotFoundException("Dispositivo not exists!!!");
		} else {
			return dRepo.findById(id).get();
		}
	}

	public List<Dispositivo> findAllDispositivi() {
		return (List<Dispositivo>) dRepo.findAll();
	}

	public void removeUtente(Dispositivo u) {
		if (!dRepo.existsById(u.getId_dipositivo())) {
			throw new EntityNotFoundException("Dispositivo not exists!!!");
		} else {
			dRepo.delete(u);
		}
	}

	public String removeDispositivoById(Long id) {
		if (!dRepo.existsById(id)) {
			throw new EntityNotFoundException("Dispositivo not exists!!!");
		} else {
			dRepo.deleteById(id);
			return "Dispositivo eliminato";
		}
	}

	public Page<Dispositivo> findAllDispositivoPageble(Pageable pag) {

		Page<Dispositivo> res = dRepo.findAll(pag);
		return res;

	}
}
