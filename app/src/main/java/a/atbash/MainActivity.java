package a.atbash;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = (Button)findViewById((R.id.button));
        System.out.println("start");
        //SQLiteDatabase db = SQLiteDatabase.openDatabase("AtbashClient", null, 0);
        //db.execSQL("create table if not exists sampletable (name text, location text)");
        //db.execSQL("insert into sampletable values('ram','hyd')");

        DataBase myDbHelper;
        myDbHelper = new DataBase(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){
            throw new Error("Noam! Second Try");

        }
        System.out.println("before");
        Cursor cursor = myDbHelper.x();
        cursor.moveToFirst();
        String name = cursor.getString(0);
        String name2 = cursor.getString(1);
        System.out.println(name);
        System.out.println(name2);
        System.out.println("hi");
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
}
