package com.example.project05_classfundmanager.myAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.memberActivity.ListMemberPaid_MemberActivity;
import com.example.project05_classfundmanager.model.ContentOfMoneyToBePay;

import java.util.List;

public class ListMoneyMuchPayToMemberAdapter extends RecyclerView.Adapter<ListMoneyMuchPayToMemberAdapter.mHolder>{
private Context context;
private List<ContentOfMoneyToBePay> list;

public ListMoneyMuchPayToMemberAdapter(Context context){
    this.context = context;
}
public void setData(List<ContentOfMoneyToBePay> list){
    this.list = list;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_show_list_spending_to_member, parent, false);
        return new mHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mHolder holder, int position) {
        if(list != null){
            ContentOfMoneyToBePay content = list.get(position);
            holder.tvName.setText(content.getName());
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String notify = "Ngày: " + content.getDate() + "\n" + "Số tiền: " + content.getMoney() + "\n" + "Ghi chú: " + content.getNote();
                    int id = content.getId();
                    String name = content.getName();
                    double money = content.getMoney();
                    Intent intent = new Intent(context, ListMemberPaid_MemberActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("money", money);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
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
            tvName = itemView.findViewById(R.id.tvRowListSpendingToMember);
        }
    }
}
