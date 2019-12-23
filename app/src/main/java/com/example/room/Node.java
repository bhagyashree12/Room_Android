package com.example.room;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data")
public class Node {

    @PrimaryKey(autoGenerate = true)
    private String id;



    private String title;
    private String description;
    private int priority;


    public Node(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //setter for the id
    public void setId(String id) {
        this.id = id;
    }

    //getters for all other variable
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }


}
