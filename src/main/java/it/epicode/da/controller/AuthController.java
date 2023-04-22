package it.epicode.da.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.da.payload.JWTAuthResponse;
import it.epicode.da.payload.LoginDto;
import it.epicode.da.payload.RegisterDto;
import it.epicode.da.service.AuthService;



@RestController
@RequestMapping("/user/auth")
public class AuthController {
	  private AuthService authService;

	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	    // Build Login REST API
	    @PostMapping(value = {"/login", "/signin"})
	    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
	           	
	    	String token = authService.login(loginDto);

	        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
	        jwtAuthResponse.setUsername(loginDto.getUsername());
	        jwtAuthResponse.setAccessToken(token);

	        return ResponseEntity.ok(jwtAuthResponse);
	    }

	    // Build Register REST API
	    @PostMapping(value = {"/register", "/signup"})
	    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
	        String response = authService.register(registerDto);
	        System.out.println(response);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
}
