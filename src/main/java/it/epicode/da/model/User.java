package it.epicode.da.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;
	@Column(nullable = false, unique = true)
	private String username;
	private String name;
	private String lastname;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	//@JsonManagedReference(value = "utente_dispositivo")
	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name = "dispositivi", referencedColumnName = "id_dipositivo")
	
	private Set<Dispositivo> dispositivi;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
		private Set<Role> roles = new HashSet<>();
	
	public void addDipositivo (Dispositivo d) {
		this.dispositivi.add(d);
		System.out.println("aggiunto");
	}
	
}
