package com.example.kingj.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    public static final int result_code=4;
    public static final String TITLE_KEY ="title";
    public static final String DATE_KEY="date";
    public static final String DIS_KEY="discription";

    Button bt;
    EditText et1,et2,et3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        bt = findViewById(R.id.bt);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String title=bundle.getString("title");
        String date = bundle.getString("date");
        String dis  = bundle.getString("discription");

        et1.setText(title);
        et2.setText(date);
        et3.setText(dis);
    }
}
