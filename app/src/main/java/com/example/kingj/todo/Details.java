package com.example.kingj.todo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    String taskname;
    String duedate;
    String discription;
    long id;
    int position;


    public static final int Result_code_for_edit=4;
    public static final int request_code_for_edit=4;

    public static final String position_k="podition";
    public static final String task_k="title";
    public static final String date_k="date";
    public static final String id_k ="id";
    public static final String discription_k="discription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

         taskname = intent.getStringExtra(MainActivity.task_k);
         duedate = intent.getStringExtra(MainActivity.date_k);
         position=intent.getIntExtra(MainActivity.position_k,1);
         id=intent.getIntExtra(MainActivity.id_k,1);

        Log.d("Details","Position = " +position);

         discription="abc";

        TextView textView = findViewById(R.id.etv1);
        textView.setText(taskname + "");
        TextView textView1 = findViewById(R.id.etv2);
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


            Intent intent = new Intent(this,Edit.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,request_code_for_edit);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(request_code_for_edit==requestCode)
        {
            if(resultCode== Edit.result_code)
            {
                String title = data.getStringExtra(Edit.TITLE_KEY);
                String dateString = data.getStringExtra(Edit.DATE_KEY);
                String discriptionString = data.getStringExtra(Edit.DIS_KEY);
                String p =String.valueOf(position);
//                int amount = Integer.parseInt(amountString);

                TextView textView = findViewById(R.id.etv1);
                textView.setText(title + "");
                TextView textView1 = findViewById(R.id.etv2);
                textView1.setText(dateString + "");
                TextView textView2=findViewById(R.id.etv3);
                textView2.setText(discriptionString + "");

                Log.d("Details","Position = " +p);

                data.putExtra(position_k,p);
                data.putExtra(task_k,title);
                data.putExtra(date_k,dateString);
                data.putExtra(id_k,id);

                setResult(request_code_for_edit, data);
                finish();
               // Task task = new Task(title,dateString);
            }
        }
    }
}
