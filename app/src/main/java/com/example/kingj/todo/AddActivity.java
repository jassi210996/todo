package com.example.kingj.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    public static int result_code=2;
    public static String TITLE_KEY="TASK TITLE";
    public static String DATE_KEY="DUE DATE";

    String title,date,descrip,time;

    EditText et1;
    EditText etdate;
    EditText ettime;
    Button bt;
    String actionmsg="android.provider.Telephony.SMS_RECEIVED";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bt = findViewById(R.id.bt);

        et1=findViewById(R.id.et1);
        etdate=findViewById(R.id.etdate);
        ettime=findViewById(R.id.ettime);

        bt.setOnClickListener(this);

        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        ettime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                setTime();
            }
        });


//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {

                Toast.makeText(AddActivity.this,"Intent received",Toast.LENGTH_LONG).show();
//                Toast.makeText("Intent received", "vkmv",Toast.LENGTH_LONG).show();
                Log.i("Intent received","00000000000");
                 intent = getIntent();
                String action = intent.getAction();
                String type = intent.getType();

                if(Intent.ACTION_SEND.equals(action) && type != null)
                {
                    Log.i("Intent received","0000");
//                    Bundle bundle = intent.getExtras();
                    String title = intent.getStringExtra(Intent.EXTRA_TEXT);

                    if (title!=null)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String date = sdf.format(new Date());

                        sdf = new SimpleDateFormat("HH:mm");
                        String str = sdf.format(new Date());
                        et1.setText(title);
                        ettime.setText(str);
                        etdate.setText(date);
                    }
                }


//            }
//        };

//        IntentFilter intentFilter = new IntentFilter("Intent.Action_Send");
//
//        registerReceiver(broadcastReceiver,intentFilter);

        Intent intent1 = getIntent();
      Bundle bundle = intent1.getExtras();
      bundle.getString("title");
      bundle.getString("date");


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
                etdate.setText(date);
            }
        },year,month,day);

        datePickerDialog.show();
    }

    public void setTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour =calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddActivity.this, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {

                time = hour + ":" + min;
                ettime.setText(time);

            }
        },hour,min,true);

        timePickerDialog.show();
    }


    @Override
    public void onClick(View view) {

         title =et1.getText().toString();
         date = etdate.getText().toString();

         if(intent.getAction().equals(Intent.ACTION_SEND)){

             //int amount = Integer.parseInt(amountString);
             Task task1 = new Task(title, date);


             TaskOpenHolder openHolder1 = TaskOpenHolder.getHolder(getApplicationContext());
             SQLiteDatabase database1 = openHolder1.getWritableDatabase();

             ContentValues contentValues = new ContentValues();

             contentValues.put(Contract.Tasks.COLUMN_TITLE, task1.getTask());
             contentValues.put(Contract.Tasks.DUE_DATE, task1.getDuedate());

             long id1 = database1.insert(Contract.Tasks.TABLE_NAME, null, contentValues);

             task1.setId(id1);

         }
         else
         {
             Intent intent = new Intent();
             intent.putExtra(TITLE_KEY,title);
             intent.putExtra(DATE_KEY,date);
             setResult(result_code,intent);
         }


        finish();

    }
}