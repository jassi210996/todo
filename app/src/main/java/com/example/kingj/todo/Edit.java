package com.example.kingj.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Edit extends AppCompatActivity implements View.OnClickListener {

    public static final int result_code=4;
    public static final String TITLE_KEY ="title";
    public static final String DATE_KEY="date";
    public static final String DIS_KEY="discription";

    String date,time;

    Button bt;
    EditText et1,et2,editTime,editDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        bt = findViewById(R.id.bt);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et3);
        editDate=findViewById(R.id.editDate);
        editTime=findViewById(R.id.editTime);

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setTime();
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });




        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String title=bundle.getString("title");
        String date = bundle.getString("date");
        String dis  = bundle.getString("discription");

        et1.setText(title);
        et2.setText(dis);
        editDate.setText(date);


        bt.setOnClickListener(this);
    }


    public void setDate()
    {
        Calendar calendar =Calendar.getInstance();

        int day = calendar.get(Calendar.DATE);
        final int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                ++month;
                date = day + "/" + month + "/" + year;
                editDate.setText(date);
            }
        },year,month,day);

        datePickerDialog.show();
    }

    public void setTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour =calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Edit.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {

                time = hour + ":" + min;
                editTime.setText(time);

            }
        },hour,min,true);

        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {

        String titleName=et1.getText().toString();
        String dueDate = editDate.getText().toString();
        String disc=et2.getText().toString();

        Intent data =new Intent();
        data.putExtra(TITLE_KEY,titleName);
        data.putExtra(DATE_KEY,dueDate);
        data.putExtra(DIS_KEY,disc);

        setResult(result_code,data);
        finish();

    }
}
