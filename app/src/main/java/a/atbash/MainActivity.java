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


        System.out.println("hi");
        //Stage x = myDbHelper.getStage(1);
        //System.out.println(x.getAnswer());
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
}
