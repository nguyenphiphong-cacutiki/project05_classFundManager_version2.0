package com.example.project05_classfundmanager.myActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.User;
import com.example.project05_classfundmanager.mySharedPre.MySharedPre;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    private EditText etName, etPhone, etClass, etPass, etVerifyPass;
    private Button btSignUp;
    private TextView tvNotifyVerifyPass;
    private MySharedPre mySharedPre;
    private long keyDatabaseAccess = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mappingAndInitializeVariable();
        btSignUp.setClickable(true);
        etVerifyPass.addTextChangedListener(textVerifyPass);
        etPass.addTextChangedListener(textVerifyPass);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String mClass = etClass.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String verifyPass = etVerifyPass.getText().toString().trim();
                User user = new User();

                if(user.isUserExists(SignUpActivity.this, phone)){
                    Toast.makeText(SignUpActivity.this, "Số điện thoại đã được dùng để đăng ký!", Toast.LENGTH_SHORT).show();
                }else if(!name.isEmpty() && !phone.isEmpty() && !mClass.isEmpty() && !pass.isEmpty() &&
                        !verifyPass.isEmpty() && pass.equals(verifyPass)){
                    // create key database access
                    keyDatabaseAccess = createKeyDatabaseAccess();
                    // save information user
                    /*mySharedPre.addName(phone, name);
                    mySharedPre.addClass(phone, mClass);
                    mySharedPre.addPass(phone, pass);
                    mySharedPre.addUser(phone);
                    mySharedPre.addKeyDatabaseAccess(phone, keyDatabaseAccess);*/

                    // create user member
                    String userMember = phone + mClass + MySharedPre.KEY_IDENTIFIED_MEMBER;
                    String passMember = String.valueOf(createPassForMember());
                    user.insertUserToDatabase(getApplicationContext(), phone, pass, name, mClass, keyDatabaseAccess, userMember, passMember);
                    /*// save user member
                    mySharedPre.addUserMember(keyDatabaseAccess, userMember);
                    // save pass member
                    mySharedPre.addPassMember(userMember, String.valueOf(createPassForMember()));
                    // add key database access for member
                    mySharedPre.addKeyDatabaseAccess(userMember, keyDatabaseAccess);*/
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(SignUpActivity.this, "Thông tin không hợp lệ!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private TextWatcher textVerifyPass = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String mClass = etClass.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            String verifyPass = etVerifyPass.getText().toString().trim();

            if(!pass.equals(verifyPass)){
                tvNotifyVerifyPass.setVisibility(View.VISIBLE);
            }else{
                tvNotifyVerifyPass.setVisibility(View.GONE);
            }
        }
    };

    private void mappingAndInitializeVariable(){
        etName = findViewById(R.id.etSignUpName);
        etPhone = findViewById(R.id.etSignUpPhone);
        etClass = findViewById(R.id.etSignUpClass);
        etVerifyPass = findViewById(R.id.etSignUpVerifyPass);
        tvNotifyVerifyPass = findViewById(R.id.tvVerifyPass);
        btSignUp = findViewById(R.id.btSignUp);
        etPass = findViewById(R.id.etSignUpPass);
        // initialize variable
        mySharedPre = new MySharedPre(getApplicationContext());
    }
    private long createKeyDatabaseAccess(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyddmmHHss");
        String keyString = simpleDateFormat.format(Calendar.getInstance().getTime());
        Log.d("seeKeyDatabase", keyString);
        return Long.parseLong(keyString);
    }
    private long createPassForMember(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ssmmddyyyy");
        String keyString = simpleDateFormat.format(Calendar.getInstance().getTime());
        Log.d("seePassMember", keyString);
        return Long.parseLong(keyString);
    }
}