package com.example.startdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Username.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table Users(first TEXT,last TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop Table if exists Users");
    }

    public Boolean insertuserdata(String first,String last)
    {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("first",first);
        contentValues.put("last",last);
        long result=db.insert("Users",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean updateuserdata(String first,String last)
    {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("last",last);
        Cursor cursor=db.rawQuery("Select * from Users where first = ?",new String[]{first});
        if(cursor.getCount()>0)
        {
            long result=db.update("Users",contentValues,"first=?",new String[]{first});
            if (result == -1)
            {
                return false;
            } else
            {
                return true;
            }
        } else
        {
            return false;
        }
    }

    public Boolean deletedata(String first)
    {
        SQLiteDatabase db =getWritableDatabase();

        Cursor cursor=db.rawQuery("Select * from Users where first=?",new String[]{first});
        if(cursor.getCount()>0)
        {
            long result=db.delete("Users","first=?",new String[] {first});
            if (result == -1)
            {
                return false;
            } else
            {
                return true;
            }
        } else
        {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase db =getWritableDatabase();

        Cursor cursor=db.rawQuery("Select * from Users",null);
        return cursor;

    }

}
