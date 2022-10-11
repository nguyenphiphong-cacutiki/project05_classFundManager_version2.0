package com.example.project05_classfundmanager.myActivity;

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
import com.example.project05_classfundmanager.model.ContentOfPaid;
import com.example.project05_classfundmanager.myAdapter.ListSpendingAdapter;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;
import com.example.project05_classfundmanager.mySharedPre.MySharedPre;

import java.util.ArrayList;
import java.util.List;

public class ListSpendingActivity extends AppCompatActivity {
private RecyclerView rcvList;
private TextView tvBackHome, tvMoneyCut;
private FundDatabase database;
private ListSpendingAdapter adapter;
private List<ContentOfPaid> list;
private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_spending);
        mappingAndInitializeVariable();
        loadDataForRecycleView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvList.setLayoutManager(linearLayoutManager);
        adapter.setData(list);
        rcvList.setAdapter(adapter);

        // load money
        List<Integer> idMoneyList = new ArrayList<>();
        List<Double> valueMoneyList = new ArrayList<>();
        // get list id
        Cursor moneyListData = database.getData("SELECT id, money FROM ContentOfMoneyToBePaid WHERE keyDatabase = '"+ MySharedPre.keyDatabaseAccess+"'");
        while (moneyListData.moveToNext()){
            idMoneyList.add(moneyListData.getInt(0));
            valueMoneyList.add(moneyListData.getDouble(1));
        }
        // get sum money
        Double sumMoney = 0.0;
        for(int i = 0; i< idMoneyList.size(); i++){
            Cursor numberOfMemberData = database.getData("SELECT COUNT(type) FROM PersonHasPayed WHERE keyDatabase = '"+MySharedPre.keyDatabaseAccess+"' AND type = '"+idMoneyList.get(i)+"'");
            if(numberOfMemberData.moveToNext()){
                sumMoney += numberOfMemberData.getInt(0) * valueMoneyList.get(i);
            }
        }

//        Cursor dataSumMoney = database.getData("SELECT SUM(money) FROM ContentOfMoneyToBePaid");
        Cursor dataCutMoney = database.getData("SELECT SUM(money) FROM ContentOfMoneyPaid WHERE keyDatabase = '"+MySharedPre.keyDatabaseAccess+"'");
        if(dataCutMoney.moveToNext()){
            int cut =(int) (sumMoney - dataCutMoney.getDouble(0));
            tvMoneyCut.setText("Số tiền còn lại là: "+ cut);
        }



        // back to home

    }
    private void mappingAndInitializeVariable(){
        rcvList = findViewById(R.id.rcvListSpending);
        tvBackHome = findViewById(R.id.tvHomeListSpending);
        tvMoneyCut = findViewById(R.id.tvMoneyCutListSpending);
        relativeLayout = findViewById(R.id.ActivityListSpendingInLogo);

        // initialize variable
        database = new FundDatabase(this);
        adapter = new ListSpendingAdapter(this);
        list = new ArrayList<>();
    }
    public void loadDataForRecycleView(){
        list.clear();
        Cursor data = database.getData("SELECT * FROM ContentOfMoneyPaid WHERE keyDatabase = '"+MySharedPre.keyDatabaseAccess+"'");
        while(data.moveToNext()){
            list.add(new ContentOfPaid(data.getInt(0), data.getString(1), data.getDouble(2),
                    data.getString(3), data.getString(4)));
        }

    }
}