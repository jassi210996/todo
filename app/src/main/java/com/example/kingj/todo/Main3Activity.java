package com.example.kingj.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    public static int result_code=2;
    public static String TITLE_KEY="TASK TITLE";
    public static String DATE_KEY="DUE DATE";

    EditText et1;
    EditText et2;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bt = findViewById(R.id.bt);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        bt.setOnClickListener(this);

        Intent intent = getIntent();
      Bundle bundle = intent.getExtras();
      bundle.getString("title");
      bundle.getString("date");


    }

    @Override
    public void onClick(View view) {

        String tiitle =et1.getText().toString();
        String date = et2.getText().toString();

        Intent data =new Intent();
        data.putExtra(TITLE_KEY,tiitle);
        data.putExtra(DATE_KEY,date);

        setResult(result_code,data);
        finish();
    }
}
