package com.example.kingj.todo;

import android.content.Context;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter {

    ArrayList<Task> items;
    LayoutInflater inflater;

    public TaskAdapter(@NonNull Context context, ArrayList<Task> items) {
        super(context, 0, items);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items=items;
    }

    @Override
    public int getCount()
    {
       return items.size();
    }

    @Override
    public long getItemId(int position )
    {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View output = inflater.inflate(R.layout.listview,parent,false);
        TextView tv1 =output.findViewById(R.id.tv1);
        TextView tv2 = output.findViewById(R.id.tv2);
        Task task =items.get(position);
        tv1.setText(task.getTask());
        tv2.setText(task.getDuedate());
        return output;
    }
}
