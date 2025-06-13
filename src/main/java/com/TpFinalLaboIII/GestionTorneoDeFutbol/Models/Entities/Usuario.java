package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email(message = "Email invalido")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLEUSER roleuser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_DT", referencedColumnName = "idDT")
    private DT dt;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank @Email(message = "Email invalido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email(message = "Email invalido") String email) {
        this.email = email;
    }

    public @NotNull ROLEUSER getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(@NotNull ROLEUSER roleuser) {
        this.roleuser = roleuser;
    }

    public DT getDt() {
        return dt;
    }

    public void setDt(DT dt) {
        this.dt = dt;
    }
}
