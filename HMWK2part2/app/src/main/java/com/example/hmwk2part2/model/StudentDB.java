package com.example.hmwk2part2.model;

import com.example.hmwk2part2.TechnicalServices.DatabaseClass;
import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB ourInstance = new StudentDB();

    private ArrayList<Student> mStudents;

    public static StudentDB getInstance() {return ourInstance;}

    private StudentDB(){createStudentObjects();}

    public ArrayList<Student> getStudents(){return mStudents;}

    public void setStudents(ArrayList<Student> students){mStudents = students;}

    private void createStudentObjects(){
        mStudents = DatabaseClass.getInstance().retrieveStudentObjects();
    }
}
