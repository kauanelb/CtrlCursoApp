package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.ui.aluno;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.R;
import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model.Aluno;
import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model.Curso;


public class CadAlunoFragment extends Fragment implements View.OnClickListener {
    private EditText etNome;
    private EditText etDtMatricula;
    private EditText etDtNascimento;
    private EditText etTelefone;
    private Spinner spCurso;
    private Button btSalvar;
    private RequestQueue requestQueue;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_aluno, container, false);
        this.spCurso = (Spinner) view.findViewById(R.id.spCurso);
        //edit texts
        this.etNome = (EditText)view.findViewById(R.id.etNome);
        this.etDtMatricula = (EditText)view.findViewById(R.id.etDtMatricula);
        this.etDtNascimento = (EditText)view.findViewById(R.id.etDtNascimento);
        this.etTelefone = (EditText)view.findViewById(R.id.etTelefone);
        this.btSalvar = (Button)view.findViewById(R.id.btSalvar);
        this.btSalvar.setOnClickListener(this);
        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();
        //Chamar o metodo pra popular o spiner
        try {
            this.consultaCurso();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //return default
        return this.view;
    }

    private void consultaCurso() throws JSONException {
        //requisição para o Rest Server
        JsonArrayRequest jsonArrayReq = null;
        try {
            jsonArrayReq = new JsonArrayRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/conCurso.php",
                    new JSONArray("[{}]"),
                    response -> {
                        try {
                            //se a consulta não veio vazia
                            if (response != null) {
                                //array list para receber a resposta
                                ArrayList<Curso> lista = new ArrayList<>();
                                //preenchendo ArrayList com JSONArray recebido
                                for (int pos = 0;pos < response.length() ;pos++){
                                    JSONObject jo = response.getJSONObject(pos);
                                    Curso curso = new Curso();
                                    curso.setIdCurso(jo.getInt("idCurso"));
                                    curso.setNmSilga(jo.getString("nmSigla"));
                                    curso.setNmCurso(jo.getString("nmCurso"));
                                    curso.setNrCargaHoraria(jo.getString("nrCargaHoraria"));
                                    lista.add(pos, curso);
                                }
                                //Criando um adapter para o spinner
                                ArrayAdapter<Curso> adapter = new ArrayAdapter<>(
                                        requireContext(),
                                        android.R.layout.simple_spinner_dropdown_item,
                                        lista);
                                //colocando o adapter no spinner
                                this.spCurso.setAdapter(adapter);
                            } else {
                                Snackbar mensagem = Snackbar.make(view,
                                        "A consulta não retornou nenhum registro!",
                                        Snackbar.LENGTH_LONG);
                                mensagem.show();
                            }
                        } catch (JSONException e) {
                            Snackbar mensagem = Snackbar.make(view,
                                    "Ops!Problema com o arquivo JSON: " + e,
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    },
                    error -> {
                        //mostrar mensagem que veio do servidor
                        Snackbar mensagem = Snackbar.make(view,
                                "Ops! Houve um problema ao realizar a consulta: " +
                                        error.toString(), Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
            );
        } catch (JSONException e) {throw new RuntimeException(e);}
        //colocando nova request para fila de execução
        requestQueue.add(jsonArrayReq);
    }

    private void cadastrarAluno(Aluno aluno) throws JSONException {
        //requisição para o Rest Server
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2/cadAluno.php",
                aluno.toJsonObject(),
                response -> {
                    try {
                        //se o request não veio vazio
                        if (response != null) {
                            Context context = requireContext();
                            if (response.getBoolean("success")) {
                                //limpar campos da tela
                                this.etNome.setText("");
                                this.etDtMatricula.setText("");
                                this.etDtNascimento.setText("");
                                this.etTelefone.setText("");
                                //primeiro item dos spinners
                                this.spCurso.setSelection(0);
                            }
                            //mostrando a mensagem que veio do JSON
                            Toast toast = Toast.makeText(
                                    view.getContext(),
                                    response.getString("message"),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
//mostrar mensagem do response == null
                            Snackbar mensagem = Snackbar.make(
                                    view,
                                    "A consulta não retornou nada!",

                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    } catch (Exception e) {
                        //mostrar mensagem da exception
                        Snackbar mensagem = Snackbar.make(
                                view,
                                "Ops!Problema com o arquivo JSON: " + e,
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
                },
                error -> {
                    //mostrar mensagem que veio do servidor
                    Snackbar mensagem = Snackbar.make(
                            view,
                            "Ops! Houve um problema: " + error.toString(),
                            Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
        );
        //colocando nova request para fila de execução
        requestQueue.add(jsonObjectReq);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btSalvar) {
            try {
                //instanciando objeto de negócio
                Aluno aluno = new Aluno();
                //populando objeto com dados da tela
                aluno.setNome(this.etNome.getText().toString());
                aluno.setDtMatricula(this.etDtMatricula.getText().toString());
                aluno.setDtNascimento(this.etDtNascimento.getText().toString());
                aluno.setTelefone(this.etTelefone.getText().toString());

                int pos = this.spCurso.getSelectedItemPosition();
                Curso curso = (Curso) spCurso.getItemAtPosition(pos);
                aluno.setIdCurso(curso.getIdCurso());

                //chamada do web service de cadastro
                try {
                    cadastrarAluno(aluno);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
