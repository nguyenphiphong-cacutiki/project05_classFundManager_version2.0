package com.example.project05_classfundmanager.memberActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.PersonHasPayed;
import com.example.project05_classfundmanager.myAdapter.ListMemberPaid_MemberAdapter;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListMemberPaid_MemberActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rcvList;
    private FundDatabase fundDatabase;
    private ListMemberPaid_MemberAdapter adapter;
    RelativeLayout relativeLayout;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_member_paid_member);
        // mapping and initialize variable
        tvTitle = findViewById(R.id.tvTitleListMemberPaid_Member);
        rcvList = findViewById(R.id.rcvListMemberPaid_Member);
        relativeLayout = findViewById(R.id.ActivityListMoneyHasPayedMemberLogo);
        fundDatabase = new FundDatabase(this);
        adapter = new ListMemberPaid_MemberAdapter(this);
        // get data from intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        double money = intent.getDoubleExtra("money", 0);
        id = intent.getIntExtra("id", 0);
        tvTitle.setText(title + ": " + (int) money);
        // set rcv
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvList.setLayoutManager(linearLayoutManager);
        adapter.setData(getData());
        rcvList.setAdapter(adapter);



        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMemberPaid_MemberActivity.this, MenuMemberActivity.class));
            }
        });


    }
    private List<PersonHasPayed> getData(){
        List<PersonHasPayed> list = new ArrayList<>();
        Cursor data = fundDatabase.getData("SELECT * FROM PersonHasPayed WHERE type = '"+id+"'");
        while(data.moveToNext()){
            list.add(new PersonHasPayed(data.getInt(0), data.getInt(1), data.getString(2),
                    data.getString(3), data.getString(4)));
        }
        return list;
    }
}