package com.example.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Node.class, version = 1)
public abstract class Node_database extends RoomDatabase {

    private static Node_database instance;

    public abstract Node_dao node_dao();

    public static synchronized Node_database getInstance(Context context) {

        if (instance == null) {
            //i cannot use new instance as its a abract class i cannot make object of it
            instance = Room.databaseBuilder(context.getApplicationContext(), Node_database.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();


        }
        return instance;

    }

    //this is used to make tables in db just like oncreate in sqilite
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populatingdb(instance).execute();
        }
    };

    private static class Populatingdb extends AsyncTask<Void, Void, Void> {
        private Node_dao node_dao;

        private Populatingdb(Node_database db) {
            node_dao = db.node_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            node_dao.insert(new Node("Title 1", "Description 1", 1));
            node_dao.insert(new Node("Title 2", "Description 2", 2));
            node_dao.insert(new Node("Title 3", "Description 3", 3));
            return null;


            
        }
    }


}
