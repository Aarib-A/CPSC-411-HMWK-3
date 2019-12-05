package com.example.hmwk2part2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmwk2part2.R;
import com.example.hmwk2part2.StudentDetailActivity;
import com.example.hmwk2part2.model.CourseEnrollment;
import com.example.hmwk2part2.model.Student;
import com.example.hmwk2part2.model.StudentDB;

public class CoursesSummaryLVAdapter extends BaseAdapter {

    private int studentIndex;

    public CoursesSummaryLVAdapter(int studentIndex){this.studentIndex = studentIndex;}

    @Override
    public int getCount(){return StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().size();}

    @Override
    public Object getItem(int i){return StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().get(i);}

    @Override
    public long getItemId(int i){return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){

        View row_view;

        if (view == null) {
            // cnt++;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.course_row, viewGroup, false);
        } else row_view = view;

        CourseEnrollment p = StudentDB.getInstance().getStudents().get(studentIndex).getmCourses().get(i);

        TextView firstNameView = (TextView) row_view.findViewById(R.id.course_id);
        firstNameView.setText(p.getCourseID());
//        firstNameView;
        TextView lastNameView = (TextView) row_view.findViewById(R.id.grade);
        lastNameView.setText(p.getGrade());
//        TextView cwid = (TextView) row_view.findViewById(R.id.cwid);
//        cwid.setText(Integer.toString(p.getCWID()));

        row_view.setTag(new Integer(i));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;


    }
}
