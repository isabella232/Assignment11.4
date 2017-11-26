package com.niketgoel.assignment114;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by niketgoel on 26/11/17.
 */

public class Dbhandler extends SQLiteOpenHelper {

    /**
     * public constructor to get the context
     * @param context
     */
    public Dbhandler(Context context) {
        super(context, "students2.db", null, 2);
    }

    /**
     * Creating table
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE TABLE_STUDENTS(COLUMN_ID TEXT,COLUMN_ID_NUM TEXT,COLUMN_FIRSTNAME TEXT,COLUMN_FIRSTNAME_VALUE TEXT,COLUMN_LASTNAME TEXT,COLUMN_LASTNAME_VALUE TEXT)");
        Log.e("Dbhandler", "Table created");
        db.execSQL(query);


    }

    /**
     * Upgrading db table and query
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = ("DROP TABLE IF EXISTS TABLE_STUDENTS");
        db.execSQL(query);
        onCreate(db);

    }

    /**
     * Adding data to table
     * @param students
     * @return
     */
    public boolean addValues(Students students) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COLUMN_ID", students.mId);
        contentValues.put("COLUMN_ID_NUM",students.mIdnum);
        contentValues.put("COLUMN_FIRSTNAME", students.mFirstname);
        contentValues.put("COLUMN_FIRSTNAME_VALUE", students.mFirstnamevalue);
        contentValues.put("COLUMN_LASTNAME", students.mLastname);
        contentValues.put("COLUMN_LASTNAME_VALUE", students.mLastnamevalue);
        long row = db.insert("TABLE_STUDENTS", null, contentValues);
        Log.e("Dbhandler", "value is inserted in row num " + row);
        db.close();
        return true;
    }

    public ArrayList<Students> databaseToArray() {
        {
            ArrayList<Students> studentslist = new ArrayList<Students>();
            String selectQuery = "SELECT * FROM TABLE_STUDENTS";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Students students = new Students();
                    students.setmId(cursor.getString(0));
                    students.setmIdnum(cursor.getString(1));
                    students.setmFirstname(cursor.getString(2));
                    students.setmFirstnamevalue(cursor.getString(3));
                    students.setmLastname(cursor.getString(4));
                    students.setmLastnamevalue(cursor.getString(5));


                    studentslist.add(students);
                } while (cursor.moveToNext());
            }
            db.close();
            return studentslist;
        }

    }
}







