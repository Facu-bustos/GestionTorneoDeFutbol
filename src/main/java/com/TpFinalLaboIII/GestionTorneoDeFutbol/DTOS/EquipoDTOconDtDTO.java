package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;


public class EquipoDTOconDtDTO {

    private long idDT;
    private String nombreDT;
    private ESTILODEJUEGO estilodejuegoDT;
    private ROLEUSER roleuserDT ;
    private EquipoDTO equipoDTO;

    public EquipoDTOconDtDTO(long idDT, String nombreDT, ESTILODEJUEGO estilodejuegoDT, ROLEUSER roleuserDT, EquipoDTO equipoDTO) {
        this.idDT = idDT;
        this.nombreDT = nombreDT;
        this.estilodejuegoDT = estilodejuegoDT;
        this.roleuserDT = roleuserDT;
        this.equipoDTO = equipoDTO;
    }

    public EquipoDTOconDtDTO(){

    }

    public long getIdDT() {
        return idDT;
    }

    public void setIdDT(long idDT) {
        this.idDT = idDT;
    }

    public String getNombreDT() {
        return nombreDT;
    }

    public void setNombreDT(String nombreDT) {
        this.nombreDT = nombreDT;
    }

    public ESTILODEJUEGO getEstilodejuegoDT() {
        return estilodejuegoDT;
    }

    public void setEstilodejuegoDT(ESTILODEJUEGO estilodejuegoDT) {
        this.estilodejuegoDT = estilodejuegoDT;
    }

    public ROLEUSER getRoleuserDT() {
        return roleuserDT;
    }

    public void setRoleuserDT(ROLEUSER roleuserDT) {
        this.roleuserDT = roleuserDT;
    }

    public EquipoDTO getEquipoDTO() {
        return equipoDTO;
    }

    public void setEquipoDTO(EquipoDTO equipoDTO) {
        this.equipoDTO = equipoDTO;
    }
}
