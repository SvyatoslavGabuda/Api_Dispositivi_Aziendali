package it.epicode.da.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.epicode.da.enums.StatoDispositivo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Setter
@Getter
@Builder
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_dipositivo;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;
	private String nomeDispositivo;
	@JsonIgnoreProperties("dispositivi")
	//@JsonBackReference(value = "utente_dispositivo")	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
}
