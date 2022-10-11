package com.example.project05_classfundmanager.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.PersonHasPayed;

import java.util.List;

public class ListMemberPaid_MemberAdapter extends RecyclerView.Adapter<ListMemberPaid_MemberAdapter.mHolder>{

    private Context context;
    private List<PersonHasPayed> list;

    public ListMemberPaid_MemberAdapter(Context context){
        this.context = context;
    }
    public void setData(List<PersonHasPayed> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_member_paid_to_member, parent, false);

        return new mHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mHolder holder, int position) {
        if(list != null){
            PersonHasPayed person = list.get(position);
            holder.tvName.setText(person.getName());
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String notify = "Ngày: " + person.getDate() +"\n"+ "Ghi chú: " +person.getNote();
                    Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class mHolder extends RecyclerView.ViewHolder{
        private TextView tvName;

        public mHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRowMemberPaid_Member);
        }
    }
}
