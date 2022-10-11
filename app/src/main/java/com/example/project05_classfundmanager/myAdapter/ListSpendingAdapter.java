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
import com.example.project05_classfundmanager.model.ContentOfPaid;

import java.util.List;

public class ListSpendingAdapter extends RecyclerView.Adapter<ListSpendingAdapter.SpendingHolder> {
    private Context context;
    private List<ContentOfPaid> list;
    
    public ListSpendingAdapter(Context context){
        this.context = context;
    }
    public void setData(List<ContentOfPaid> list){
        this.list = list;
    }
    @NonNull
    @Override
    public SpendingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_spending, parent, false);
        return new SpendingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendingHolder holder, int position) {
        if(list != null){
            ContentOfPaid contentOfPaid = list.get(position);
            holder.tvName.setText(contentOfPaid.getName());
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String notify = "Ngày: " + contentOfPaid.getDate() + "\n" +
                            "Số tiền: " + contentOfPaid.getMoney() + "\n" +
                            "Ghi chú: " + contentOfPaid.getNote();
                    Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Heyyy!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class SpendingHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView imgDelete;
        public SpendingHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRowListSpending);
            imgDelete = itemView.findViewById(R.id.imgDeleteRowSpending);
        }
    }
}
