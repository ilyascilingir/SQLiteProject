package com.ilyascilingir.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musician",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR,age INT)");

            database.execSQL("INSERT INTO musicians (name,age) VALUES('James',50)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES('Lars',60)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES('Kirk',55)");

            database.execSQL("UPDATE musicians SET age=61 WHERE name = 'Lars'");
            database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE name = 'Kirk'");

            database.execSQL("DELETE from musicians  WHERE name = 'Lars'");

            //Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s' ",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%' ",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'Lars' ",null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIX = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIX));
            }
            cursor.close();

        }catch (Exception e){
        e.printStackTrace();
            Log.d("sql hata: ", e.getMessage());
    }

    }
}