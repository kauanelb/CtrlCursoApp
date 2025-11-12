package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.ui.aluno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.R;


public class CadAlunoFragment extends Fragment {
    private EditText etNome;
    private EditText etDtMatricula;
    private EditText etDtNascimento;
    private EditText etTelefone;
    private Spinner spCurso;
    private Button btSalvar;

    public CadAlunoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cad_aluno, container, false);
    }
}