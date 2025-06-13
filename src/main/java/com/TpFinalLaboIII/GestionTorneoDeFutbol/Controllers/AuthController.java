package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.LoginRequest;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.LoginResponse;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Security.CustomUserDetails;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Security.CustomUserDetailsService;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; // para validar credenciales

    @Autowired
    private JwtService jwtService; // para generar token

    @Autowired
    private CustomUserDetailsService userDetailsService; // para cargar datos usuario

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            // 1. Validar credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Cargar detalles del usuario
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // 3. Generar token JWT
            String jwt = jwtService.generateToken(userDetails);

            // 4. Devolver token y datos usuario (puede ser DTO personalizado)
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername(), userDetails.getUsuario().getRoleuser()));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }


    }
}
