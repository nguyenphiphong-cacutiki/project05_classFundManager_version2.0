package com.example.project05_classfundmanager.memberActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.ContentOfMoneyToBePay;
import com.example.project05_classfundmanager.model.User;
import com.example.project05_classfundmanager.myAdapter.ListMoneyMuchPayToMemberAdapter;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListMoneyHasPayMemberActivity extends AppCompatActivity {
    private RecyclerView rcvList;
    private FundDatabase fundDatabase;
    private ListMoneyMuchPayToMemberAdapter adapter;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_money_has_pay_member);
        // mapping and initialize variable
        rcvList = findViewById(R.id.rcvListMoneyMuchPayMember);
        relativeLayout = findViewById(R.id.ActivityListMoneyHasPayedMemberLogo);
        fundDatabase = new FundDatabase(this);
        adapter = new ListMoneyMuchPayToMemberAdapter(this);

        //
        adapter.setData(getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvList.setLayoutManager(linearLayoutManager);
        rcvList.setAdapter(adapter);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMoneyHasPayMemberActivity.this, MenuMemberActivity.class));
            }
        });

    }
    private List<ContentOfMoneyToBePay> getData(){
        List<ContentOfMoneyToBePay> list = new ArrayList<>();
        Cursor data = fundDatabase.getData("SELECT * FROM ContentOfMoneyToBePaid WHERE keyDatabase = '"+ User.keyDataBase +"'");
        while (data.moveToNext()){
            list.add(new ContentOfMoneyToBePay(data.getInt(0), data.getString(1), data.getDouble(2),
                    data.getString(3), data.getString(4)));
        }
        return list;
    }
}