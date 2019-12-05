package com.example.hmwk2part2.model;

public class CourseEnrollment {
    private String mCourseID;
    private String mGrade;

    public CourseEnrollment(String CourseID, String Grade){
        mCourseID = CourseID;
        mGrade = Grade;
    }

    public String getCourseID(){return mCourseID;}
    public void setCourseID(String CourseID){mCourseID = CourseID;}

    public String getGrade(){return  mGrade;}
    public void setGrade(String Grade){mGrade = Grade;}
}
