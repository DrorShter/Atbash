package a.atbash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InstractionsActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instractions);
    }
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(InstractionsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
