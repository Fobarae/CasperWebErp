package Entities;

import java.util.ArrayList;

public class Worker {

    int workerid;
    String name;
    String email;
    ArrayList<Task> todo;

    Worker(String name,String email,ArrayList<Task> todo){
    this.name=name;
    this.email=email;
    this.todo=todo;
    }

    public Worker(int id,String name,String email){
        this.workerid=id;
        this.name=name;
        this.email=email;
        this.todo=new ArrayList<Task>();


    }



    //Add a task to a worker
    public  void addTodo(Task todo){
    this.todo.add(todo);
    }

    //check if a project contains the selected worker
    boolean checkProject(Project project){
        if(project.getWorkers().containsKey(this)){
        return true;
        }
        else {
            return false;
        }
    }
    //gets the todo of a Certain Worker in a project
    ArrayList<Task> getProjectTodo(Project project){
        if(checkProject(project)){
            return project.getWorkers().get(this);
        }
        else{
            System.out.println("Customer is not in the project!! ");
            return null;
        }
    }

    public ArrayList<Task> getTodo() {
        return todo;
    }

    //Changes the worker on a current task
    //Take

    void reassign(Task task){
        //get the global list of the workers
        //select one of them
        //assign him the selected task and remove it from the current worker
    this.getTodo().remove(task);

    }



}
