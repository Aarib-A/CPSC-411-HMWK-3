package com.example.hmwk2part2.model;

import java.util.ArrayList;

public class Student {
    private String mFirstName;
    private String mLastname;
    private int mCWID;
    private ArrayList<CourseEnrollment> mCourses;

    public Student(String mFirstName, String mLastname, int mCWID){
        this.mFirstName = mFirstName;
        this.mLastname = mLastname;
        this.mCWID = mCWID;
    }

    public String getFirstName(){return mFirstName;}
    public void setFirstName(String firstName){mFirstName = firstName;}

    public String getLastName(){return mLastname;}
    public void setLastName(String lastName){mLastname = lastName;}

    public int getCWID(){return mCWID;}
    public void setCWID(int CWID){mCWID = CWID;}

    public void setmCourses(ArrayList<CourseEnrollment> courses){mCourses = courses;}
    public ArrayList<CourseEnrollment> getmCourses(){return mCourses;}

}
