package it.epicode.da.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.epicode.da.enums.StatoDispositivo;
import it.epicode.da.model.Dispositivo;


@Configuration
public class DispositivoConfig {
	@Bean("ParamDispositivo")
	@Scope("prototype")
	public Dispositivo paramDispositivo(String nome, StatoDispositivo s) {
		Dispositivo d = new Dispositivo();
		d.setNomeDispositivo(nome);
		d.setStatoDispositivo(s);
		return d;
	}
	@Bean("FakeDispositivo")
	@Scope("prototype")
	public Dispositivo fakeDispositivo() {
		Faker fake = Faker.instance(new Locale("it-It"));
		Dispositivo d = new Dispositivo();
		d.setNomeDispositivo(fake.cat().name()+" dispositivo");
		int randN = (int) ((Math.random()*4)+1);
		switch (randN) {
		case 1: {
			d.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
			break;
		}
		case 2: {
			d.setStatoDispositivo(StatoDispositivo.DISMESSO);
			break;
		}
		case 3: {
			d.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
			break;
		}
		case 4: {
			d.setStatoDispositivo(StatoDispositivo.IN_MANUTENZIONE);
			break;
		}
		
		default:
			
			d.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
		}
		return d;
	}
}
