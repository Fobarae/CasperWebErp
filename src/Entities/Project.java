package Entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Project extends AboveGod {

    String name;
    Date dueDate;
    float price;
    int workforce;
    String filename;
    String id;

    public Project(String name, Date dueDate, float price, int workforce){
        setName(name);
        setDueDate(dueDate);
        setPrice(price);
        setWorkforce(workforce);
        setId();

    }

    public String[] toStringArray(){
        String[] result=new String[4];
        result[0]=getName();
        result[1]=getDueDate().toString();
        result[2]=String.valueOf(getPrice());
        result[3]=String.valueOf(getWorkforce());
        return result;
    }

    public void setId() {
        this.id =String.valueOf(ThreadLocalRandom.current().nextInt(0, 10000 + 1));
    }

    public String getId() {
        return id;
    }

    public Project(){}

    public Project(Project copy){
        setName(copy.getName());
        setDueDate(copy.getDueDate());
        setPrice(copy.getPrice());
        setWorkforce(copy.getWorkforce());
    }





    long getCountdown(){
        LocalDate d1 = LocalDate.parse("LocalDate.now()", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse("this.getDueDate()", DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        return diffDays;

    }





























    public Date getDueDate() {
        return dueDate;
    }

    public float getPrice() {
        return price;
    }

    public int getWorkforce() {
        return workforce;
    }

    public String getName() {
        return name;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setWorkforce(int workforce) {
        this.workforce = workforce;
    }
}