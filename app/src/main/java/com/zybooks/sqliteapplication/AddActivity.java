package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private EditText className;
    private EditText classDescription;
    private EditText classInstructor;
    private EditText classLocation;
    private EditText classDate;
    private EditText classTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        className = findViewById(R.id.classNameText);
        classDescription = findViewById(R.id.classDescriptionText);
        classInstructor = findViewById(R.id.classInstructorText);
        classLocation = findViewById(R.id.classLocationText);
        classDate = findViewById(R.id.classDateText);
        classTime = findViewById(R.id.classTimeText);
    }

    public void onAddButtonClicked (View view) {
        Intent intent = getIntent();
        intent.putExtra("name", className.getText().toString());
        intent.putExtra("description", classDescription.getText().toString());
        intent.putExtra("instructor", classInstructor.getText().toString());
        intent.putExtra("location", classLocation.getText().toString());
        intent.putExtra("date", classDate.getText().toString());
        intent.putExtra("time", classTime.getText().toString());

        setResult(RESULT_OK, intent);

        finish();
    }
}