package com.example.project05_classfundmanager.myActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project05_classfundmanager.R;
import com.example.project05_classfundmanager.model.PersonHasPayed;
import com.example.project05_classfundmanager.myAdapter.PersonHasPayAdapter;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;
import com.example.project05_classfundmanager.mySharedPre.MySharedPre;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OneTypeMoneyMustPayActivity extends AppCompatActivity {
    private String mode = "";
    private int id = 0;
    private List<PersonHasPayed> listPerson;
    private FundDatabase fundDatabase;
    private TextView tvTitle;
    private ImageView imgAdd;
    private RecyclerView rcvList;
    private PersonHasPayAdapter adapter;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_type_money_must_pay);
        mappingAndInitializeVariable();
        Intent intent = getIntent();
        mode = intent.getStringExtra("ANDROID");
        if(mode.equals("0")){
            Cursor data = fundDatabase.getData("SELECT * FROM ContentOfMoneyToBePaid WHERE id = (SELECT MAX(id) FROM ContentOfMoneyToBePaid) AND keyDatabase = '"+MySharedPre.keyDatabaseAccess+"'");
            if(data.moveToNext()){
                id = data.getInt(0);
                String content = data.getString(1);
                int money = (int) data.getDouble(2);
                String date = data.getString(3);
                String note = data.getString(4);

                // set data
                tvTitle.setText(content + ": "+money);
                String notify = "Ngày: "+date + "\n" + "Ghi chú: "+note;
                tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(OneTypeMoneyMustPayActivity.this, notify, Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }else if(mode.equals("1")){
            id = intent.getIntExtra("idOfMoney", 0);
            String nameOfContent = intent.getStringExtra("nameOfContent");
            int money = (int) intent.getDoubleExtra("Money", 0);
            tvTitle.setText(nameOfContent+": "+money);
        }
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddNewPersonPayed();
            }
        });
        // load data for recycle view
        loadDataForRecycleView();
        adapter.setData(listPerson);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvList.setLayoutManager(linearLayoutManager);
        rcvList.setAdapter(adapter);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneTypeMoneyMustPayActivity.this, MainMenuActivity.class));
            }
        });
    }
    private void mappingAndInitializeVariable(){
        //mapping
        tvTitle = findViewById(R.id.tvNameOfOneType);
        imgAdd = findViewById(R.id.imvAddPerson);
        rcvList = findViewById(R.id.rcvListPersonHasPaid);
        relativeLayout = findViewById(R.id.ActivityOneTypeMoneyMustPayLogo);

        // initialize variable
        fundDatabase = new FundDatabase(this);
        listPerson = new ArrayList<>();
        adapter = new PersonHasPayAdapter(this);
    }
    private void loadDataForRecycleView(){
        listPerson.clear();
        Cursor data = fundDatabase.getData("SELECT * FROM PersonHasPayed WHERE type = '"+id+"' AND keyDatabase = '"+MySharedPre.keyDatabaseAccess+"'");
        while(data.moveToNext()){
            listPerson.add(new PersonHasPayed(data.getInt(0), data.getInt(1), data.getString(2),
                    data.getString(3), data.getString(4)));
        }
    }
    private void dialogAddNewPersonPayed(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_new_person_payed);
        // mapping
        EditText etName = dialog.findViewById(R.id.etNameDialogAddNewSpending);
        EditText etIdStudent = dialog.findViewById(R.id.etMoneyDialogAddNewSpending);
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
                String idStudent = etIdStudent.getText().toString().trim();
                String date = tvDate.getText().toString().trim();
                String note = etNote.getText().toString().trim();

                if(name.isEmpty() || idStudent.isEmpty() || date.isEmpty() || note.isEmpty()){
                    Toast.makeText(OneTypeMoneyMustPayActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    PersonHasPayed personHasPayed = new PersonHasPayed(id, name, date, note);
                    fundDatabase.insertPersonHasPayed(personHasPayed);
                    loadDataForRecycleView();
                    adapter.notifyDataSetChanged();
                    dialog.cancel();

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
}