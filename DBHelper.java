package com.example.projecttwocs360brandonhornick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper{
    // variables for database creation
    public static final String DBName = "register.db";
    private static final String TABLE_WEIGHT = "weight_entries";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_NOTES = "notes";

    // initialize dbhelper
    public DBHelper(@Nullable Context context){
        super(context, DBName, null, 1);
    }
    //create table with sql query
    @Override
    public void onCreate(SQLiteDatabase sqldb){
        sqldb.execSQL("create table users(username TEXT primary key, password TEXT)");
        String CREATE_WEIGHT_TABLE = "CREATE TABLE " + TABLE_WEIGHT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_WEIGHT + " TEXT,"
                + COLUMN_NOTES + " TEXT" + ")";
        sqldb.execSQL(CREATE_WEIGHT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1){
        sqldb.execSQL("drop table if exists users");
    }

    //insert user data for login screen
    public boolean insertData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //check if user name is taken before allowing user to sign up with it
    public boolean checkUserName(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    // actual login function, checks for valid credentials
    public boolean checkUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    // insert data to the weight table
    public boolean insertWeightEntry(String date, String weight, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("weight", weight);
        values.put("notes", notes);
        long result = db.insert("weight_entries", null, values);
        return result != -1;
    }

    // used to display the data on the journal page
    public Cursor getAllWeightEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM weight_entries", null);
    }
    // delete an entry from the table
    public boolean deleteWeightEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete("weight_entries", "id = ?", new String[]{String.valueOf(id)});
        return deletedRows > 0;
    }
}
