package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;

import java.util.Objects;

public class UserDTO {

    private Long idUsuario;
    private String username;
    private String password;
    private String email;
    private ROLEUSER roleuser;

    public UserDTO(Long idUsuario, String username, String password, String email, ROLEUSER roleuser) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleuser = roleuser;
    }

    public UserDTO()
    {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "AdmDTO{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleuser=" + roleuser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getIdUsuario(), userDTO.getIdUsuario()) && Objects.equals(getUsername(), userDTO.getUsername()) && Objects.equals(getPassword(), userDTO.getPassword()) && Objects.equals(getEmail(), userDTO.getEmail()) && getRoleuser() == userDTO.getRoleuser();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuario(), getUsername(), getPassword(), getEmail(), getRoleuser());
    }
}
