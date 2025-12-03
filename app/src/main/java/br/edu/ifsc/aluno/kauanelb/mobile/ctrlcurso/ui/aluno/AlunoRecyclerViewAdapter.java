package br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.ui.aluno;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.databinding.FragmentConAlunoBinding;
import br.edu.ifsc.aluno.kauanelb.mobile.ctrlcurso.model.Aluno;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Aluno}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AlunoRecyclerViewAdapter extends RecyclerView.Adapter<AlunoRecyclerViewAdapter.ViewHolder> {

    private final List<Aluno> mValues;

    public AlunoRecyclerViewAdapter(List<Aluno> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConAlunoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNome());
        holder.mContentView.setText(mValues.get(position).getTelefone());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Aluno mItem;

        public ViewHolder(FragmentConAlunoBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}