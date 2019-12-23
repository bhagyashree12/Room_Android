package com.example.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//dao class has to be interface
@Dao
public interface Node_dao {

    @Insert
    void insert(Node n);

    @Update
    void update(Node n);

    @Delete
    void delete(Node n);

    //when room does have the function u wought to perform make your own by using query
    @Query("DELETE FROM data")
    void delete_all();

    @Query("SELECT * FROM data ORDER BY priority DESC")
    LiveData<List<Node>> getAllData();
}
