package com.example.project05_classfundmanager.myAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.ContentOfMoneyToBePay;
import com.example.project05_classfundmanager.myActivity.OneTypeMoneyMustPayActivity;

import java.util.List;

public class ListMoneyHasPayAdapter extends RecyclerView.Adapter<ListMoneyHasPayAdapter.ListMoneyHolder> {
    private Context context;
    private List<ContentOfMoneyToBePay> list;

    public ListMoneyHasPayAdapter(Context context){
        this.context = context;
    }
    public void setData(List<ContentOfMoneyToBePay> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListMoneyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_money_has_pay, parent, false);
        return new ListMoneyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMoneyHolder holder, int position) {
        if(list != null){
            ContentOfMoneyToBePay content = list.get(position);
            holder.tvName.setText(content.getName() + ": "+ (int)content.getMoney());

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OneTypeMoneyMustPayActivity.class);
                    intent.putExtra("idOfMoney", content.getId());
                    intent.putExtra("nameOfContent", content.getName());
                    intent.putExtra("ANDROID", "1");
                    intent.putExtra("Money", content.getMoney());
                    context.startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class ListMoneyHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView imgDelete;
        public ListMoneyHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRowNameListMoneyHasPay);
            imgDelete = itemView.findViewById(R.id.imgRowDeleteListMoneyHasPay);
        }
    }
}
