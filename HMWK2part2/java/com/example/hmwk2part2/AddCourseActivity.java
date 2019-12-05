package com.example.hmwk2part2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hmwk2part2.model.CourseEnrollment;
import com.example.hmwk2part2.model.Student;
import com.example.hmwk2part2.model.StudentDB;

public class AddCourseActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int studentIndex;
    protected final String TAG = "Add Course Screen (AddCourseActivity.java)";

    private boolean hasCourseBeenSetBefore = false;
    private int indexOfNewCourseObject;

    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.add_new_course_form);

        studentIndex = getIntent().getIntExtra(StudentDetailActivity.LocationOfStudentIndex, 0);

//        Student sObj = StudentDB.getInstance().getStudents().get(studentIndex);
        EditText newCourseNameView = (EditText) findViewById(R.id.new_course_name_id);
        EditText newLetterGradeView = (EditText) findViewById(R.id.new_grade_letter_id);

        newCourseNameView.setEnabled(true);
        newLetterGradeView.setEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_course_screen_menu, menu);
        detailMenu = menu;

//        menu.findItem(R.id.action_student_edit).setVisible(false);
        menu.findItem(R.id.action_add_new_course_done).setVisible(true);
        menu.findItem(R.id.action_edit_new_course).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText newCourseNameView = (EditText) findViewById(R.id.new_course_name_id);
        EditText newLetterGradeView = (EditText) findViewById(R.id.new_grade_letter_id);
        switch (item.getItemId()){
            case R.id.action_add_new_course_done:

                if (!hasCourseBeenSetBefore){
                    CourseEnrollment ceObject = new CourseEnrollment(newCourseNameView.getText().toString(),
                            newLetterGradeView.getText().toString());
                    StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().add(ceObject);
                    hasCourseBeenSetBefore = true;
                    indexOfNewCourseObject = StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().indexOf(ceObject);
                }else{
                    StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().get(indexOfNewCourseObject).setCourseID(newCourseNameView.getText().toString());
                    StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().get(indexOfNewCourseObject).setGrade(newLetterGradeView.getText().toString());
                }

                newCourseNameView.setEnabled(false);
                newLetterGradeView.setEnabled(false);

                detailMenu.findItem(R.id.action_add_new_course_done).setVisible(false);
                detailMenu.findItem(R.id.action_edit_new_course).setVisible(true);

                break;

            case R.id.action_edit_new_course:

                newCourseNameView.setEnabled(true);
                newLetterGradeView.setEnabled(true);

                detailMenu.findItem(R.id.action_add_new_course_done).setVisible(true);
                detailMenu.findItem(R.id.action_edit_new_course).setVisible(false);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
