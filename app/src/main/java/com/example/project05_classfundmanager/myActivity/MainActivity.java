package com.example.project05_classfundmanager.myActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.memberActivity.MenuMemberActivity;
import com.example.project05_classfundmanager.model.User;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;
import com.example.project05_classfundmanager.mySharedPre.MySharedPre;

public class MainActivity extends AppCompatActivity {
    private EditText etPhone, etPass;
    private TextView tvSignIn, tvSignUp;
    private MySharedPre mySharedPre;
    private FundDatabase fundDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingAndInitializeVariable();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userLogin = etPhone.getText().toString().trim();
                String passLogin = etPass.getText().toString().trim();
                if(userLogin.isEmpty() || passLogin.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    User mUser = new User();
                        Cursor data = fundDatabase.getData("SELECT * FROM User WHERE user = '"+userLogin+"' OR userMember = '"+userLogin+"'");
                        if(data.moveToNext()){
                            User.user = data.getString(1);
                            User.pass = data.getString(2);
                            User.mName = data.getString(3);
                            User.mClass = data.getString(4);
                            User.keyDataBase = data.getLong(5);
                            User.userMember = data.getString(6);
                            User.passMember = data.getString(7);
                            MySharedPre.keyDatabaseAccess = User.keyDataBase;
                            if(userLogin.equals(User.user) && passLogin.equals(User.pass)){
                                startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
                            }else if(userLogin.equals(User.userMember) && passLogin.equals(User.passMember)){
                                startActivity(new Intent(MainActivity.this, MenuMemberActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Mật khẩu không đúng, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                        Toast.makeText(MainActivity.this, "Tên đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void mappingAndInitializeVariable(){
        // mapping
        etPass = findViewById(R.id.etSignInPass);
        etPhone = findViewById(R.id.etSignInPhone);
        tvSignIn = findViewById(R.id.tvButtonSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);
        fundDatabase = new FundDatabase(this);

        // initialize variable
        mySharedPre = new MySharedPre(getApplicationContext());
    }
}