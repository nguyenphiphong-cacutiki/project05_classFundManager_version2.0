package com.example.project05_classfundmanager.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.PersonHasPayed;

import java.util.ArrayList;
import java.util.List;

public class PersonHasPayAdapter extends RecyclerView.Adapter<PersonHasPayAdapter.PersonHolder> {
    private List<PersonHasPayed> list;
    private Context context;


    public PersonHasPayAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }
    public void setData(List<PersonHasPayed> mList){
        this.list = mList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PersonHasPayAdapter.PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_person_has_payed, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        if(list !=null){
            PersonHasPayed person = list.get(position);
            holder.tvName.setText(person.getName());
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String notify = "Ngày: " + person.getDate() + "\n" +
                            "Ghi chú: " + person.getNote();
                    Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "heh!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    
    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class PersonHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView imgDelete;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRowPersonHasPayed);
            imgDelete = itemView.findViewById(R.id.imgDeleteRowPersonHasPayed);
        }
    }
}
