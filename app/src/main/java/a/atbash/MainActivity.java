package a.atbash;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        File temp = new File("AtbashClient.db");

        SQLiteDatabase db =openOrCreateDatabase(temp.getAbsolutePath(), MODE_PRIVATE, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = (Button)findViewById((R.id.button));
        DataBase d = new DataBase(db);
        d.getLast();
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
}
