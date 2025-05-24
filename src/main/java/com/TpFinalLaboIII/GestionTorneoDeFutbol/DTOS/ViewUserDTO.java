package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;

import java.util.Objects;

public class ViewUserDTO {
    private Long idUsuario;
    private String username;
    private String email;
    private ROLEUSER roleuser;

    public ViewUserDTO(Long idUsuario, String username, String email, ROLEUSER roleuser) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.email = email;
        this.roleuser = roleuser;
    }

    public ViewUserDTO() {

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ROLEUSER getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(ROLEUSER roleuser) {
        this.roleuser = roleuser;
    }

    @Override
    public String toString() {
        return "viewUserDTO{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roleuser=" + roleuser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewUserDTO that)) return false;
        return Objects.equals(getIdUsuario(), that.getIdUsuario()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail()) && getRoleuser() == that.getRoleuser();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuario(), getUsername(), getEmail(), getRoleuser());
    }
}
