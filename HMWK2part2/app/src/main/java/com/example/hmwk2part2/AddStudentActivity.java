package com.example.hmwk2part2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hmwk2part2.model.Student;
import com.example.hmwk2part2.model.StudentDB;

public class AddStudentActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int studentIndex;
    protected final String TAG = "Add Student Screen (AddStudentActivity.java)";




    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.activity_student_detail);

        studentIndex = getIntent().getIntExtra("StudentIndex", 0);

//        Student sObj = StudentDB.getInstance().getStudents().get(studentIndex);
        EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);
//        fNameView.setText(sObj.getFirstName());
//        lNameView.setText(sObj.getLastName());
//        cwidView.setText(Integer.toString(sObj.getCWID()));
        fNameView.setEnabled(true);
        lNameView.setEnabled(true);
        cwidView.setEnabled(true);

        // Need to implement to show courses
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, menu);
        detailMenu = menu;

//        menu.findItem(R.id.action_student_edit).setVisible(false);
        menu.findItem(R.id.action_student_done).setVisible(true);
        menu.findItem(R.id.action_add_student).setVisible(false);

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
//            detailMenu.findItem(R.id.action_student_edit).setVisible(false);
            detailMenu.findItem(R.id.action_student_done).setVisible(true);
            detailMenu.findItem(R.id.action_add_student).setVisible(false);
//            Log.d(TAG, "Unintended \"edit\" button clicked on menu");
        } else if (item.getItemId() == R.id.action_student_done) {
            EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
            EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
            EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);
//            StudentDB.getInstance().getStudents().get(studentIndex).setFirstName(fNameView.getText().toString());
//            StudentDB.getInstance().getStudents().get(studentIndex).setLastName(lNameView.getText().toString());
//            StudentDB.getInstance().getStudents().get(studentIndex).setCWID(Integer.parseInt(cwidView.getText().toString()));

            Student sObj = new Student(fNameView.getText().toString(),lNameView.getText().toString(),
                            Integer.parseInt(cwidView.getText().toString()));

            StudentDB.getInstance().getStudents().add(sObj);

            fNameView.setEnabled(false);
            lNameView.setEnabled(false);
            cwidView.setEnabled(false);
//            detailMenu.findItem(R.id.action_student_edit).setVisible(true);
            detailMenu.findItem(R.id.action_student_done).setVisible(false);
            detailMenu.findItem(R.id.action_add_student).setVisible(false);
        }else if (item.getItemId() == R.id.action_add_student) {
            Log.d(TAG, "Unintended \"Add Student\" button clicked on menu");
        }


        return super.onOptionsItemSelected(item);
    }

}
