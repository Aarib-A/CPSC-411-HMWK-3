package com.example.hmwk2part2.TechnicalServices;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

import com.example.hmwk2part2.StudentSummaryLVActivity;
import com.example.hmwk2part2.model.CourseEnrollment;
import com.example.hmwk2part2.model.Student;

import java.io.File;
import java.util.ArrayList;

public class DatabaseClass{
    private String fileDBname = "hmwk3.db";
    private File dbFilePath;
    private SQLiteDatabase myDB;

    private static final DatabaseClass ourInstance = new DatabaseClass();

    public static DatabaseClass getInstance(){return ourInstance;}

    private DatabaseClass(){


        dbFilePath = StudentSummaryLVActivity.contextVar.getDatabasePath(fileDBname);

        if(!dbFilePath.exists()) {
            myDB = SQLiteDatabase.openOrCreateDatabase(dbFilePath, null);

            myDB.execSQL("CREATE TABLE IF NOT EXISTS Student (fName Text, lName, CWID INTEGER)");
            myDB.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CWID INTEGER, CouseID Text, Grade Text)");

            myDB.execSQL("INSERT INTO Student VALUES ('Nathan', 'Drake', 105678455)");
            myDB.execSQL("INSERT INTO CourseEnrollment VALUES (105678455, 'CPSC 411', 'A+')");
            myDB.execSQL("INSERT INTO CourseEnrollment VALUES (105678455, 'CPSC 440', 'C')");

            myDB.execSQL("INSERT INTO Student VALUES ('Ezio', 'Auditore', 456789123)");
            myDB.execSQL("INSERT INTO CourseEnrollment VALUES (105678455, 'CPSC 411', 'A')");
            myDB.execSQL("INSERT INTO CourseEnrollment VALUES (105678455, 'CPSC 440', 'A-')");

            myDB.close();
        }
    }

    public ArrayList<Student> retrieveStudentObjects(){


        Student studentObject;
        ArrayList<Student> studentList = new ArrayList<Student>();
        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();

        myDB = SQLiteDatabase.openOrCreateDatabase(dbFilePath, null);

        Cursor cursor = myDB.query("Student",null,null,null,null,null,null);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String fName = cursor.getString(cursor.getColumnIndex("fName"));
                String lName = cursor.getString(cursor.getColumnIndex("lName"));
                int CWID = cursor.getInt(cursor.getColumnIndex("CWID"));

                studentObject = new Student(fName,lName,CWID);

                Cursor innerCursor = myDB.query("CourseEnrollment",null,null,null,null,null,null);
                if (innerCursor.getCount() > 0){
                    while (innerCursor.moveToNext()){
                        int foriegnKeyCWID = innerCursor.getInt(innerCursor.getColumnIndex("CWID"));
                        String courseName = innerCursor.getString(innerCursor.getColumnIndex("CourseID"));
                        String gradeLetter = innerCursor.getString(innerCursor.getColumnIndex("Grade"));

                        if (CWID == foriegnKeyCWID) courses.add(new CourseEnrollment(courseName, gradeLetter));
                    }
                }
                innerCursor.close();
                studentObject.setmCourses(courses);
                studentList.add(studentObject);
            }
        }
        cursor.close();
        myDB.close();
        return studentList;
    }

}
