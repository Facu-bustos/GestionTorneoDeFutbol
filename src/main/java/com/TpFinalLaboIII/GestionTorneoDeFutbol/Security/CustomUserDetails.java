package com.TpFinalLaboIII.GestionTorneoDeFutbol.Security;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asignamos el rol del usuario como autoridad
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRoleuser().name()));
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        // Usamos el email como nombre de usuario para login
        return usuario.getEmail();
    }

    // Estos métodos los podés personalizar según tu lógica de negocio

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Cuenta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Cuenta nunca bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Credenciales nunca expiran
    }

    @Override
    public boolean isEnabled() {
        return true;  // Usuario habilitado
    }

    // Getter para acceder a la entidad Usuario si necesitás
    public Usuario getUsuario() {
        return usuario;
    }
}
