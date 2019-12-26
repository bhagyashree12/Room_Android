package com.example.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Node_viewmodel extends AndroidViewModel {
    private Node_repository repository;
    private LiveData<List<Node>> allnotes;

    public Node_viewmodel(@NonNull Application application) {
        super(application);

        repository=new Node_repository(application);
        allnotes=repository.getNode_data();

    }

    public void insert(Node node){
        repository.insert(node);
    }
    public void update(Node node){
        repository.update(node);
    }
    public void delete(Node node){
        repository.delete(node);
    }
    public void deleteall(){
        repository.delete_all();
    }
    public LiveData<List<Node>> getAllNotes() {
        return allnotes;
    }









}
