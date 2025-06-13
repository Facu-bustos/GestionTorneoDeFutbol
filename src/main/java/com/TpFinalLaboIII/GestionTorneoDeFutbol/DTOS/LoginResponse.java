package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;

public class LoginResponse {
    private String token;
    private String email;
    private ROLEUSER role;

    public LoginResponse(String token, String email, ROLEUSER role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
    // getters y setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ROLEUSER getRole() {
        return role;
    }

    public void setRole(ROLEUSER role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
}
