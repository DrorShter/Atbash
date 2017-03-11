package a.atbash;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;


public class MainActivity extends AppCompatActivity {
    Button b;
    private StageHandler stageHandler;
    private static CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (!isLoggedIn())
        {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult)
                        {
                            Toast.makeText(MainActivity.this, "Login Succeeded", Toast.LENGTH_LONG).show();

                        }
                        @Override
                        public void onCancel()
                        {
                            Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onError(FacebookException exception)
                        {
                            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends"));
        }
        b = (Button)findViewById((R.id.button));
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToStageActivity(v);
            }
        });
        try {
            IOUtils.copy(getAssets().open("AtbashClient1.db"), new FileOutputStream(getFilesDir()+"/AtbashClient2.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("jdbc:sqlite:"+getFilesDir()+"/test.db");
    }
    public void goToStageActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, StageActivity.class);
        startActivity(intent);
    }
    public void goToPreviousStagesActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, PreviousStagesActivity.class);
        startActivity(intent);
    }
    public void goToLeaderboardsActivity(View view)
    {
        if (isLoggedIn())
        {
            Intent intent = new Intent(MainActivity.this, LeaderboardsActivity.class);
            startActivity(intent);
        }
        else
        {
            String alertText = getString(R.string.cannotShowLeaderboards);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage(alertText).create().show();
        }
    }
    public boolean isLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null)
        {
            return true;
        }
        return false;
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
