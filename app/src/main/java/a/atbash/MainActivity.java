package a.atbash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    Button b;
    private StageHandler stageHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = (Button)findViewById((R.id.button));
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GoToStageActivity(v);
            }
        });
        try {
            IOUtils.copy(getAssets().open("AtbashClient1.db"), new FileOutputStream(getFilesDir()+"/AtbashClient2.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("jdbc:sqlite:"+getFilesDir()+"/test.db");

    }
    public void GoToStageActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
}
