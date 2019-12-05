package com.example.hmwk2part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hmwk2part2.adapter.StudentSummaryLVAdapter;
//import com.example.hmwk2part2.model.StudentDB;


public class StudentSummaryLVActivity extends AppCompatActivity {

    protected ListView studentSummaryView;
    protected final String TAG = "Summary Screen";
    protected StudentSummaryLVAdapter ad;

    protected Menu detailMenu;


    @Override
    protected void onCreate(Bundle savedInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstance);

        setContentView(R.layout.student_summary_listview);

        studentSummaryView = findViewById(R.id.student_summary_listView_ID);
        ad = new StudentSummaryLVAdapter();
        studentSummaryView.setAdapter(ad);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        ad.notifyDataSetChanged();
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, menu);
        detailMenu = menu;
//        menu.findItem(R.id.action_student_edit).setVisible(false);
        menu.findItem(R.id.action_student_done).setVisible(false);
        menu.findItem(R.id.action_add_student).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_student_edit) {
//            Log.d(TAG, "Unintended \"edit\" button clicked on menu");
//        } else
        if (item.getItemId() == R.id.action_student_done) {
            Log.d(TAG, "Unintended \"done\" button clicked on menu");
        } else if (item.getItemId() == R.id.action_add_student) {
            Intent intent = new Intent(this, AddStudentActivity.class);
            this.startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}


