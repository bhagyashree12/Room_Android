package com.example.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Node_repository {

    private Node_dao node_dao;
    private LiveData<List<Node>> node_data;


    //make the constructor of the class
    public Node_repository(Application application) {
        Node_database node_database = Node_database.getInstance(application);

        //abstract method of database to call dao
        node_dao = node_database.node_dao();

        //method to get live data
        node_data = node_dao.getAllData();


    }

    public void insert(Node node) {
        //innerclass is static so no need to make object
        new Insertnode(node_dao).execute(node);
    }

    public void update(Node node) {
        new Updatenode(node_dao).execute(node);
    }

    public void delete(Node node) {
        new Deletenode(node_dao).execute(node);
    }

    public void delete_all() {
        new Deleteallnode(node_dao).execute();
    }

    public LiveData<List<Node>> getNode_data() {
        return node_data;
    }


    //create the asynctask for each operation
    private static class Insertnode extends AsyncTask<Node, Void, Void> {

        private Node_dao node_dao;

        private Insertnode(Node_dao node_dao) {
            this.node_dao = node_dao;
        }

        @Override
        protected Void doInBackground(Node... nodes) {

            node_dao.insert(nodes[0]);


            return null;

        }
    }
    private static class Updatenode extends AsyncTask<Node, Void, Void> {

        private Node_dao node_dao;

        private Updatenode(Node_dao node_dao) {
            this.node_dao = node_dao;
        }

        @Override
        protected Void doInBackground(Node... nodes) {

            node_dao.update(nodes[0]);


            return null;

        }
    }
    private static class Deletenode extends AsyncTask<Node, Void, Void> {

        private Node_dao node_dao;

        private Deletenode(Node_dao node_dao) {
            this.node_dao = node_dao;
        }

        @Override
        protected Void doInBackground(Node... nodes) {

            node_dao.delete(nodes[0]);


            return null;

        }
    }
    private static class Deleteallnode extends AsyncTask<Node, Void, Void> {

        private Node_dao node_dao;

        private Deleteallnode(Node_dao node_dao) {
            this.node_dao = node_dao;
        }

        @Override
        protected Void doInBackground(Node... nodes) {

            node_dao.delete_all();


            return null;

        }
    }


}
