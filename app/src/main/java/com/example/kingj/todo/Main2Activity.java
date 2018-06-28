package com.example.kingj.todo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.zip.Inflater;

public class Main2Activity extends AppCompatActivity {

    String taskname;
    String duedate;
    String discription;
    public static final int request_code=4;
    public static final String task_k="title";
    public static final String date_k="date";
    public static final String discription_k="discription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
         taskname = intent.getStringExtra(MainActivity.task_k);
         duedate = intent.getStringExtra(MainActivity.date_k);
         discription="abc";

        TextView textView = findViewById(R.id.tv1);
        textView.setText(taskname + "");
        TextView textView1 = findViewById(R.id.tv2);
        textView1.setText(duedate + "");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.edit)
        {
            Bundle bundle=new Bundle();

            bundle.putString(task_k,taskname);
            bundle.putString(date_k,duedate);
            bundle.putString(discription_k,discription);


            Intent intent = new Intent(this,Main4Activity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,request_code);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==requestCode)
        {
            if(resultCode==Main4Activity.result_code)
            {
                String title = data.getStringExtra(Main4Activity.TITLE_KEY);
                String dateString = data.getStringExtra(Main4Activity.DATE_KEY);
                String discriptionString = data.getStringExtra(Main4Activity.DIS_KEY);
//                int amount = Integer.parseInt(amountString);
                TextView textView = findViewById(R.id.tv1);
                textView.setText(title + "");
                TextView textView1 = findViewById(R.id.tv2);
                textView1.setText(dateString + "");
                TextView textView2=findViewById(R.id.tv3);
                textView2.setText(discriptionString + "");

               // Task task = new Task(title,dateString);
            }
        }
    }
}
