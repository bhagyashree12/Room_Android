package com.example.room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {
    //name of intent string
    public static final String EXTRA_ID =
            "com.example.room.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.room.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.room.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.room.EXTRA_PRIORITY";
    private EditText edit_title;
    private EditText edit_description;
    private NumberPicker numberPicker;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        edit_title = findViewById(R.id.edit_title);
        edit_description = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("edit note");
            edit_title.setText(intent.getStringExtra(EXTRA_TITLE));
            edit_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Node");
        }


        //to show the back option
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

    }

    private void saveNote() {
        String title = edit_title.getText().toString();
        String description = edit_description.getText().toString();
        int priority = numberPicker.getValue();

        //to avoid accepting null string
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            return;
        }


        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        //getting the id value from previous intent
        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        //check whether default value is not null
        if(id!=-1){
            intent.putExtra(EXTRA_ID,id);
        }

        //this is used to show whether input was successful or not
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
