package com.example.kingj.todo;

public class Task {

    String task;
    long id;
    String duedate;

    public Task(String task, String duedate) {
        this.task = task;
        this.duedate = duedate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}
