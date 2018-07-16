package com.example.kingj.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<Task> tasks=new ArrayList<>();
    TaskAdapter adapter;
    ListView listView;


    public static String task_k="task";
    public static String date_k = "date";
    public static String position_k="position";
    public static String id_k ="id";

    public static int request_code=1;
    public static int request_code_for_edit=4;
    public static int result_code=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);


        //Intent intent = getIntent();
//


        //        Log.d("MainActivity "," id = " +id );



        TaskOpenHolder openHolder = TaskOpenHolder.getHolder(getApplicationContext());

        SQLiteDatabase database = openHolder.getReadableDatabase();

        Cursor cursor= database.query(Contract.Tasks.TABLE_NAME, null, null, null,
                                      null, null, null);

        while (cursor.moveToNext())
        {
            String title = cursor.getString(cursor.getColumnIndex(Contract.Tasks.COLUMN_TITLE));
            String due_date = cursor.getString(cursor.getColumnIndex(Contract.Tasks.DUE_DATE));
            long id = cursor.getInt(cursor.getColumnIndex(Contract.Tasks.ID));
            Task task = new Task(title,due_date);
            task.setId(id);
            tasks.add(task);

            Log.d("Main Activity", "Title = " + title + "Date = " + due_date);
        }

//        for (int i = 0; i < 5; i++)
//        {
//            Task task= new Task("Task " + i,i+"/10/2018");
//            tasks.add(task);
//        }


        adapter=new TaskAdapter(getApplicationContext(), tasks, new MyButton() {
            @Override
            public void rowButton(View view, int position) {

            }
        });

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
            Bundle bundle=new Bundle();
            bundle.putString("task","abc");
            bundle.putString("date","2/08/2018");

            Intent intent = new Intent(this,AddActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,1);



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainqActivity","Activity Result called = " + resultCode + "rq" + requestCode  );
        if(requestCode == 1){
            if(resultCode == AddActivity.result_code){


                String title1 = data.getStringExtra(AddActivity.TITLE_KEY);
                String dateString = data.getStringExtra(AddActivity.DATE_KEY);
                int year  = data.getIntExtra(AddActivity.CYEAR_KEY,1);
                int month  = data.getIntExtra(AddActivity.CMONTH_KEY,1);
                int min  = data.getIntExtra(AddActivity.CMIN_KEY,1);
                int day  = data.getIntExtra(AddActivity.CDATE_KEY,1);
                int hour  = data.getIntExtra(AddActivity.CHOUR_KEY,1);

                Log.d("MainqActivity"," = =" + result_code +"=" + dateString +"=" + hour + "=" +month);


                //int amount = Integer.parseInt(amountString);
                Task task1 = new Task(title1, dateString);


                TaskOpenHolder openHolder1 = TaskOpenHolder.getHolder(getApplicationContext());
                SQLiteDatabase database1 = openHolder1.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(Contract.Tasks.COLUMN_TITLE, task1.getTask());
                contentValues.put(Contract.Tasks.DUE_DATE, task1.getDuedate());

                long id1 = database1.insert(Contract.Tasks.TABLE_NAME, null, contentValues);

                task1.setId(id1);

                tasks.add(task1);

                adapter.notifyDataSetChanged();

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                Intent intent = new Intent(this,MyReceiver.class);
                PendingIntent pendingIntent =  PendingIntent.getBroadcast(this,1,intent,0);

                Calendar calendar = Calendar.getInstance();
                //        calendar.set(2018,8,2);
                calendar.set(year,month,day,hour,min);


                long currentTime = System.currentTimeMillis();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()/*currentTime + 5000*/,
                        10000,pendingIntent);


            }
        }
        if(requestCode == request_code_for_edit )
        {
            if(resultCode == Details.Result_code_for_edit )
            {
                String title = data.getStringExtra(Details.task_k);
                String dateString = data.getStringExtra(Details.date_k);
                String position= data.getStringExtra(Details.position_k);
                long id =data.getLongExtra(Details.id_k,1);

                //long lID = Long.parseLong(id);
                int p=Integer.parseInt(position);
                Task task = new Task(title,dateString);

                Log.d("MainActivity","Position result = " +position);

                TaskOpenHolder openHolder = TaskOpenHolder.getHolder(getApplicationContext());

                SQLiteDatabase database = openHolder.getReadableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.Tasks.COLUMN_TITLE,task.getTask());
                contentValues.put(Contract.Tasks.DUE_DATE,task.getTask());

                String[] selectionArgs={id +"" };
                database.update(Contract.Tasks.TABLE_NAME,contentValues,Contract.Tasks.ID+" = ?",selectionArgs);

//                Task task = new Task(title,dateString);
//                tasks.remove(p);
                tasks.set(p,task);
                adapter.notifyDataSetChanged();

            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Task task = tasks.get(i);
        long id = task.getId();

        String taskname=task.task;
        String duedate=task.duedate;

        int position=i;

        Log.d("Details","Position i = " +i);

        Log.d("MainActivity","Position = " +position);



        Intent intent = new Intent(this,Details.class);
        intent.putExtra(task_k,taskname);
        intent.putExtra(date_k,duedate);
        intent.putExtra(position_k,position);
        intent.putExtra(id_k,id);

        startActivityForResult(intent,request_code_for_edit);


    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        final Task task = tasks.get(i);
        final int position=i;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setCancelable(false);
        builder.setMessage("Do you really want to remove selected task?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                TaskOpenHolder openHelper = TaskOpenHolder.getHolder(getApplicationContext());
                SQLiteDatabase database = openHelper.getWritableDatabase();

                long id = task.getId();
                String[] selectionArgs = {id + ""};

                database.delete(Contract.Tasks.TABLE_NAME,Contract.Tasks.ID + " = ?",selectionArgs);


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
