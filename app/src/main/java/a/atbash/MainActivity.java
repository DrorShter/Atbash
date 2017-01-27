package a.atbash;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = (Button) findViewById((R.id.button));
        t = (TextView) findViewById(R.id.textView);
        t.setText("check");
        System.out.println("start");
    }

    public void GoToStageActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
}