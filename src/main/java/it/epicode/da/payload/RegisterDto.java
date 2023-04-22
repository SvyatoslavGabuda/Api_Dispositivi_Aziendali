package it.epicode.da.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	 private String name;
	    private String username;
	    private String email;
	   private String lastname;
	    private String password;
	    private Set<String> roles;
}
