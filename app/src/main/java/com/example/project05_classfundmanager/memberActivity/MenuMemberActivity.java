package com.example.project05_classfundmanager.memberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.myActivity.ListSpendingActivity;
import com.example.project05_classfundmanager.myActivity.MainActivity;

public class MenuMemberActivity extends AppCompatActivity {
    private TextView tvMoneySpending, tvMoneyMuchPay;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_member);
        tvMoneyMuchPay = findViewById(R.id.tvMenuMemberShowMoneyMuchPaid);
        tvMoneySpending = findViewById(R.id.tvMenuMemberShowMoneySpending);
        relativeLayout = findViewById(R.id.ActivityMenuMemberLogo);

        tvMoneySpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuMemberActivity.this, ListSpendingActivity.class));
            }
        });
        tvMoneyMuchPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuMemberActivity.this, ListMoneyHasPayMemberActivity.class));
            }
        });

        // log out
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuMemberActivity.this, MainActivity.class));
            }
        });
    }

}