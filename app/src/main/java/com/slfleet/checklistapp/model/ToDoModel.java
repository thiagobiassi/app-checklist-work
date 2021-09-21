package com.slfleet.checklistapp.model;

public class ToDoModel{
    private int id, status;
    private Long taskId;
    private String task;

    public Long getTaskId(){
        return taskId;
    }

    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}