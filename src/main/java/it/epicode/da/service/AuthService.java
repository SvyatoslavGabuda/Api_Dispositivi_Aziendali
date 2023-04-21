package it.epicode.da.service;

import it.epicode.da.payload.LoginDto;
import it.epicode.da.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
