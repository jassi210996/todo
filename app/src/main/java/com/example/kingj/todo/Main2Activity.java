package com.example.kingj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String taskname = intent.getStringExtra(MainActivity.task_k);
        String duedate = intent.getStringExtra(MainActivity.date_k);

        TextView textView = findViewById(R.id.tv1);
        textView.setText(taskname + "");
        TextView textView1 = findViewById(R.id.tv2);
        textView1.setText(duedate + "");
    }

}
