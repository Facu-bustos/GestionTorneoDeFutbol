package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;


public class EquipoDTOconDtDTO {

    private long idDT;
    private ESTILODEJUEGO estilodejuegoDT;
    private EquipoDTO equipoDTO;

    public EquipoDTOconDtDTO(long idDT, ESTILODEJUEGO estilodejuegoDT, EquipoDTO equipoDTO) {
        this.idDT = idDT;
        this.estilodejuegoDT = estilodejuegoDT;
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


    public ESTILODEJUEGO getEstilodejuegoDT() {
        return estilodejuegoDT;
    }

    public void setEstilodejuegoDT(ESTILODEJUEGO estilodejuegoDT) {
        this.estilodejuegoDT = estilodejuegoDT;
    }

    public EquipoDTO getEquipoDTO() {
        return equipoDTO;
    }

    public void setEquipoDTO(EquipoDTO equipoDTO) {
        this.equipoDTO = equipoDTO;
    }
}
