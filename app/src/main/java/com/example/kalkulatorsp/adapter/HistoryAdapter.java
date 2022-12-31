package com.example.kalkulatorsp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalkulatorsp.R;
import com.example.kalkulatorsp.database.entitas.history;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewAdapter>{
    private List<history> list;
    private Context context;
    private  Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public HistoryAdapter(Context context, List<history> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_layout,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.txtAngka1.setText(list.get(position).angka1);
        holder.txtAngka2.setText(list.get(position).angka2);
        holder.txtOperator.setText(list.get(position).operator);
        holder.txtHasil.setText(list.get(position).hasil);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView txtAngka1, txtAngka2,txtOperator,txtHasil;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            txtAngka1 = itemView.findViewById(R.id.txtangka1);
            txtAngka2 = itemView.findViewById(R.id.txtangka2);
            txtOperator = itemView.findViewById(R.id.txtoperator);
            txtHasil = itemView.findViewById(R.id.txthasil);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
