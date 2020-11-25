package com.zybooks.sqliteapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScheduleActivity extends AppCompatActivity {

    private final int ADD_REQUEST_CODE = 0;
    private final int EDIT_REQUEST_CODE = 1;

    private final String EMPTY_STRING = "";

    private ScheduleDatabase database;
    private Class _class;

    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        database = ScheduleDatabase.getInstance(this);

        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.schedule_fragment_container);

        if (fragment == null) {
            fragment = new ScheduleFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.schedule_fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_add:
                intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
                return true;
            case R.id.action_edit:
                intent = new Intent(this, EditActivity.class);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ADD_REQUEST_CODE) {
            database.addClass(data.getStringExtra("name"),
                    data.getStringExtra("description"),
                    data.getStringExtra("location"),
                    data.getStringExtra("instructor"),
                    data.getStringExtra("date"),
                    data.getStringExtra("time"));


        }

        if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {

            if (_class != null) {
                _class = database.selectClass(data.getStringExtra("id"));

                if (!data.getStringExtra("name").equals(EMPTY_STRING)) {
                    _class.setName(data.getStringExtra("name"));
                }
                if (!data.getStringExtra("description").equals(EMPTY_STRING)) {
                    _class.setDescription(data.getStringExtra("description"));
                }
                if (!data.getStringExtra("location").equals(EMPTY_STRING)) {
                    _class.setLocation(data.getStringExtra("location"));
                }
                if (!data.getStringExtra("instructor").equals(EMPTY_STRING)) {
                    _class.setInstructor(data.getStringExtra("instructor"));
                }
                if (!data.getStringExtra("date").equals(EMPTY_STRING)) {
                    _class.setDate(data.getStringExtra("date"));
                }
                if (!data.getStringExtra("time").equals(EMPTY_STRING)) {
                    _class.setTime(data.getStringExtra("time"));
                }

                database.updateClass(
                        _class.getId(),
                        _class.getName(),
                        _class.getDescription(),
                        _class.getLocation(),
                        _class.getInstructor(),
                        _class.getDate(),
                        _class.getTime());


            }
        }
    }
}