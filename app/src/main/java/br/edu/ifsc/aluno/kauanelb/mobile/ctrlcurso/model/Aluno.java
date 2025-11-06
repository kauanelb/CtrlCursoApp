package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluno {

    //atributos
    private String nome;
    private String curso;
    private String dtMatricula;
    private String dtNascimento;
    private String telefone;
    private int idCurso;

    //métodos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome.length() > 3) {
            this.nome = nome;
        } else{
            throw new Exception("Nome muito curto!");
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
            //Verificando se o String recebido é uma data válida
            SimpleDateFormat formato =
                    new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date data = (Date) formato.parse(dtMatricula);
                //se chegar até aqui não deu erro no parser
                this.dtMatricula = dtMatricula;
            } catch (ParseException e) {
                throw new IllegalArgumentException("Data inválida!");
            }
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        //Verificando se o String recebido é uma data válida
        SimpleDateFormat formato =
                new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = (Date) formato.parse(dtNascimento);
            //se chegar até aqui não deu erro no parser
            this.dtNascimento = dtNascimento;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida!");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
