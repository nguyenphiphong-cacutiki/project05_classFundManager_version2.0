package com.example.project05_classfundmanager.myActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.memberActivity.MenuMemberActivity;
import com.example.project05_classfundmanager.model.ContentOfMoneyToBePay;
import com.example.project05_classfundmanager.model.ContentOfPaid;
import com.example.project05_classfundmanager.model.User;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;
import com.example.project05_classfundmanager.mySharedPre.MySharedPre;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainMenuActivity extends AppCompatActivity {
    private TextView tvAddNewMoneyToBePay, tvListMoneyToBePay, tvAddNewMoneyPaid, tvListMoneyPaid,
    tvShowAccountForMember;
    private RelativeLayout relativeLayout;
    private FundDatabase fundDatabase;
    private MySharedPre mySharedPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mappingAndInitializeVariable();
        tvAddNewMoneyToBePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddNewMoneyToBePay();
            }
        });
        tvListMoneyToBePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ListMoneyHasPayedActivity.class));
            }
        });
        tvAddNewMoneyPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddNewSpending();
            }
        });
        tvListMoneyPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ListSpendingActivity.class));
            }
        });
        tvShowAccountForMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShowAccountForMember();
            }
        });


        // log out
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
            }
        });

    }
    private void mappingAndInitializeVariable(){
        tvAddNewMoneyPaid = findViewById(R.id.tvMenuAddNewSpending);
        tvAddNewMoneyToBePay = findViewById(R.id.tvMenuAddNewMoneyGive);
        tvListMoneyPaid = findViewById(R.id.tvMenuShowListMoneySpending);
        tvListMoneyToBePay = findViewById(R.id.tvMenuShowListMoneyGive);
        tvShowAccountForMember = findViewById(R.id.tvMenuShowAccountForMember);
        relativeLayout = findViewById(R.id.ActivityMenuMainLogo);

        // initialize variable
        fundDatabase = new FundDatabase(this);
        mySharedPre = new MySharedPre(this);
    }
    private void dialogAddNewMoneyToBePay(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_content_of_money_to_be_pay);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // mapping
        EditText name = dialog.findViewById(R.id.etNameDialogAddNewSpending);
        EditText money = dialog.findViewById(R.id.etMoneyDialogAddNewSpending);
        TextView date = dialog.findViewById(R.id.tvDateDialogAddNewSpending);
        EditText note = dialog.findViewById(R.id.etNoteDialogAddNewSpending);
        TextView btCancel = dialog.findViewById(R.id.tvCancelDialogAddNewSpending);
        TextView btOk = dialog.findViewById(R.id.tvOkDialogAddNewSpending);
        //
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDate(date);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = name.getText().toString().trim();
                String mMoney = money.getText().toString().trim();
                String mDate = date.getText().toString().trim();
                String mNote = note.getText().toString().trim();

                if(mName.isEmpty() || mMoney.isEmpty() || mDate.isEmpty() || mNote.isEmpty()){
                    Toast.makeText(MainMenuActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    ContentOfMoneyToBePay object = new ContentOfMoneyToBePay(mName, Double.parseDouble(mMoney), mDate, mNote);
                    fundDatabase.insertContentOfMoneyToBePaid(object);
                    Intent intent = new Intent(MainMenuActivity.this, OneTypeMoneyMustPayActivity.class);
                    intent.putExtra("ANDROID", "0");
                    startActivity(intent);
                }
            }
        });
        dialog.show();
    }
    private void dialogAddNewSpending(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_new_spending);
        // mapping
        EditText etName = dialog.findViewById(R.id.etNameDialogAddNewSpending);
        EditText etMoney = dialog.findViewById(R.id.etMoneyDialogAddNewSpending);
        EditText etNote = dialog.findViewById(R.id.etNoteDialogAddNewSpending);
        TextView tvDate = dialog.findViewById(R.id.tvDateDialogAddNewSpending);

        TextView btCancel = dialog.findViewById(R.id.tvCancelDialogAddNewSpending);
        TextView btOk = dialog.findViewById(R.id.tvOkDialogAddNewSpending);
        //
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDate(tvDate);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String money = etMoney.getText().toString().trim();
                String date = tvDate.getText().toString().trim();
                String note = etNote.getText().toString().trim();

                if(name.isEmpty() || money.isEmpty() || date.isEmpty() || note.isEmpty()){
                    Toast.makeText(MainMenuActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    ContentOfPaid content = new ContentOfPaid(name, Double.parseDouble(money), date, note);
                    fundDatabase.insertSpending(content);
                    startActivity(new Intent(MainMenuActivity.this, ListSpendingActivity.class));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
    private void pickerDate(TextView tvDate){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                String s = formatDate.format(calendar.getTime());
                tvDate.setText(s);
            }
        },year,month,day);
        datePickerDialog.show();
    }
    private void dialogShowAccountForMember(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_show_account_for_member);
        TextView tvUser, tvPass;
        // mapping
        tvUser = dialog.findViewById(R.id.tvRowUserMember);
        tvPass = dialog.findViewById(R.id.tvRowPassMember);
        tvUser.setText(User.userMember);
        tvPass.setText(User.passMember);
        dialog.show();
    }
}