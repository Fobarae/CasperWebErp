package Entities;

import java.sql.Date;

public class Task {
    int taskid;
    String name;
    String description;
    boolean status;
    Date todoDate;
    int project_id;


    public Task(int taskid,String name,String description,Date todoDate,boolean status,int project_id){
        this.taskid=taskid;
        this.name=name;
        this.description=description;
        this.todoDate=todoDate;
        this.status=status;
        this.project_id=project_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public String getName() {
        return name;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public int getTaskid() {
        return taskid;
    }

    public String getDescription() {
        return description;
    }
}
