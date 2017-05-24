package a.atbash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InstructionsActivity extends AppCompatActivity
{
    //This function gets Bundle and returns void
    //This function is responsible on set layout
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
    }
    //This function gets View and returns void
    //This function is responsible on returning to MainActivity
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(InstructionsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
