package com.example.kingj.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<Task> tasks=new ArrayList<>();
    TaskAdapter adapter;
    ListView listView;
    public static String task_k="task";
    public static String date_k = "date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);


        for (int i = 0; i < 5; i++)
        {
            Task task= new Task("Task " + i,i+"/10/2018");
            tasks.add(task);
        }


        adapter=new TaskAdapter(this,tasks);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id==R.id.addnew)
        {

            final int count = tasks.size();

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Add New task");
            LinearLayout linearLayout= new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            final EditText et1 = new EditText(this);
            final EditText et2 = new EditText(this);

            et1.setHint("Enter task name");
            et2.setHint("Enter due date");

            linearLayout.addView(et1);
            linearLayout.addView(et2);

            builder.setView(linearLayout);

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });

            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String nm=et1.getText().toString();
                    String dd = et2.getText().toString();

                    Task task = new Task(nm,dd);
                    tasks.add(count,task);
//                    tasks.add(task);


                }
            });
//            builder.setView();

            AlertDialog dialog=builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Task task = tasks.get(i);

        String taskname=task.task;
        String duedate=task.duedate;

        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra(task_k,taskname);
        intent.putExtra(date_k,duedate);
        startActivity(intent);


    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        final int position=i;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setCancelable(false);
        builder.setMessage("Do you really want to remove selected tas?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tasks.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
        return true;
    }
}
