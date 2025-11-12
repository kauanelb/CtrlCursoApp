package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluno {

    //atributos
    private int matricula;
    private String nome;
    private String dtMatricula;
    private String dtNascimento;
    private String telefone;
    private int idCurso;

    //construtores
    public Aluno(){
        this.matricula = 0 ;
        this.nome = "";
        this.dtMatricula = "1900-01-01";
        this.dtNascimento = "1900-01-01";
        this.telefone = "";
        this.idCurso = 0;
    }

    //CONSTRUTOR - inicializa atributo de um arquivo JSon, recebe json
    public Aluno(JSONObject jp){
        try {
            this.setMatricula(jp.getInt("nrMatricula"));
            this.setNome(jp.getString("nmAluno"));
            this.setDtMatricula(jp.getString("dtMatricula"));
            this.setDtNascimento(jp.getString("dtNascimento"));
            this.setTelefone(jp.getString("nrTelefone"));
            this.setIdCurso(jp.getInt("idCursoCadastro"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("nrMatricula", this.matricula);
            json.put("nmAluno", this.nome);
            json.put("dtMatricula", this.dtMatricula);
            json.put("dtNascimento", this.dtNascimento);
            json.put("nrTelefone", this.telefone);
            json.put("idCursoCadastro", this.idCurso);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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
