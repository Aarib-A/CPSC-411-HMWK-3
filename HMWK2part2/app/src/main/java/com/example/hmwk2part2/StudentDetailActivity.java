package com.example.hmwk2part2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.hmwk2part2.adapter.CoursesSummaryLVAdapter;
import com.example.hmwk2part2.adapter.CoursesSummaryLVAdapter;
import com.example.hmwk2part2.model.Student;
import com.example.hmwk2part2.model.StudentDB;

public class StudentDetailActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int studentIndex;
    public static final String LocationOfStudentIndex = "com.example.hmwk2part2.StudentDetailActivity.java";
    protected final String TAG = "Detail Student Screen (StudentDetailActivity.java)";

    protected View root;

    protected ListView coureSummaryView;
    protected CoursesSummaryLVAdapter ad;

    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.activity_student_detail);

        studentIndex = getIntent().getIntExtra("StudentIndex", 0);

        Student sObj = StudentDB.getInstance().getStudents().get(studentIndex);
        EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);
        fNameView.setText(sObj.getFirstName());
        lNameView.setText(sObj.getLastName());
        cwidView.setText(Integer.toString(sObj.getCWID()));
        fNameView.setEnabled(false);
        lNameView.setEnabled(false);
        cwidView.setEnabled(false);

        // Need to implement to show courses
        Button addCourseButton = (Button) findViewById(R.id.button_add_course);

        addCourseButton.setOnClickListener(
            new Button.OnClickListener() {
                @Override
                public void onClick(View viewObject) {
                    //SPAWNING NEW ACTIVITY(AppCompatActivity) HERE
//                    AddNewCourseACTIVITY();
                }
            }
        );

        coureSummaryView = findViewById(R.id.courses_listview_id);
        ad = new CoursesSummaryLVAdapter(studentIndex);
        coureSummaryView.setAdapter(ad);
    }

    private void AddNewCourseACTIVITY() {

//        Intent intentToSpawn  = new Intent(this, AddCourseActivity.class);
//        intentToSpawn.putExtra(LocationOfStudentIndex, studentIndex);
//        startActivity(intentToSpawn);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        ad.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_student_screen_menu, menu);
        detailMenu = menu;
        menu.findItem(R.id.action_student_edit).setVisible(true);
        menu.findItem(R.id.action_student_done).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_student_edit) {
            EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
            EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
            EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);
            fNameView.setEnabled(true);
            lNameView.setEnabled(true);
            cwidView.setEnabled(true);
            detailMenu.findItem(R.id.action_student_edit).setVisible(false);
            detailMenu.findItem(R.id.action_student_done).setVisible(true);
            detailMenu.findItem(R.id.action_add_student).setVisible(false);
        } else if (item.getItemId() == R.id.action_student_done) {
            EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
            EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
            EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);
            StudentDB.getInstance().getStudents().get(studentIndex).setFirstName(fNameView.getText().toString());
            StudentDB.getInstance().getStudents().get(studentIndex).setLastName(lNameView.getText().toString());
            StudentDB.getInstance().getStudents().get(studentIndex).setCWID(Integer.parseInt(cwidView.getText().toString()));
            fNameView.setEnabled(false);
            lNameView.setEnabled(false);
            cwidView.setEnabled(false);
            detailMenu.findItem(R.id.action_student_edit).setVisible(true);
            detailMenu.findItem(R.id.action_student_done).setVisible(false);
            detailMenu.findItem(R.id.action_add_student).setVisible(false);
        }
        return super.onOptionsItemSelected(item);
    }
}
