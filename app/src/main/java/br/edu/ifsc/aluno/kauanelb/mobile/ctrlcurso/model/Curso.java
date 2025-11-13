package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model;

public class Curso {

    private int idCurso;
    private String nmSilga;
    private String nmCurso;
    private String nrCargaHoraria;

    @Override
    public String toString(){
        return this.nmCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNmSilga() {
        return nmSilga;
    }

    public void setNmSilga(String nmSilga) {
        this.nmSilga = nmSilga;
    }

    public String getNmCurso() {
        return nmCurso;
    }

    public void setNmCurso(String nmCurso) {
        this.nmCurso = nmCurso;
    }

    public String getNrCargaHoraria() {
        return nrCargaHoraria;
    }

    public void setNrCargaHoraria(String nrCargaHoraria) {
        this.nrCargaHoraria = nrCargaHoraria;
    }
}
