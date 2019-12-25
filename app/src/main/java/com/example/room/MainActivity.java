package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;

    private Node_viewmodel viewmodel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final Node_Adapter adapter = new Node_Adapter();
        recyclerView.setAdapter(adapter);


        //floating button
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        viewmodel = ViewModelProviders.of(this).get(Node_viewmodel.class);
        viewmodel.getAllNotes().observe(this, new Observer<List<Node>>() {
            @Override
            public void onChanged(@Nullable List<Node> notes) {
                //update RecyclerView
                adapter.setNode(notes);
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AnotherActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AnotherActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AnotherActivity.EXTRA_PRIORITY, 1);

            Node note = new Node(title, description, priority);
            viewmodel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
