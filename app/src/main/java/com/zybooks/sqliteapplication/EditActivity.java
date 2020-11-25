package com.zybooks.sqliteapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

    private final String EMPTY_STRING = "";

    private Button editButton;
    private EditText classId;
    private EditText className;
    private EditText classDescription;
    private EditText classInstructor;
    private EditText classLocation;
    private EditText classDate;
    private EditText classTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_edit);

        classId = findViewById(R.id.classIdText);
        className = findViewById(R.id.classNameText);
        classDescription = findViewById(R.id.classDescriptionText);
        classInstructor = findViewById(R.id.classInstructorText);
        classLocation = findViewById(R.id.classLocationText);
        classDate = findViewById(R.id.classDateText);
        classTime = findViewById(R.id.classTimeText);

        editButton = findViewById(R.id.editButton);




    }

    public void onEditButtonClick (View view) {
        Intent intent = getIntent();

            intent.putExtra("id", classId.getText().toString());

            if (!className.getText().toString().equals("")) {
                intent.putExtra("name", className.getText().toString());
            } else {
                intent.putExtra("name", EMPTY_STRING);
            }
            if (!classDescription.getText().toString().equals("")) {
                intent.putExtra("description", classDescription.getText().toString());
            } else {
                intent.putExtra("description", EMPTY_STRING);
            }
            if (!classInstructor.getText().toString().equals("")) {
                intent.putExtra("instructor", classInstructor.getText().toString());
            } else {
                intent.putExtra("instructor", EMPTY_STRING);
            }
            if (!classLocation.getText().toString().equals("")) {
                intent.putExtra("location", classLocation.getText().toString());
            } else {
                intent.putExtra("location", EMPTY_STRING);
            }
            if (!classDate.getText().toString().equals("")) {
                intent.putExtra("date", classDate.getText().toString());
            } else {
                intent.putExtra("date", EMPTY_STRING);
            }
            if (!classTime.getText().toString().equals("")) {
                intent.putExtra("time", classTime.getText().toString());
            } else {
                intent.putExtra("time", EMPTY_STRING);
            }
            setResult(RESULT_OK, intent);

        finish();
    }
}
