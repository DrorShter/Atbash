package a.atbash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button b;
    private StageHandler stageHandler;
    private static CallbackManager callbackManager;
    private static boolean FirstTimeInMainActivity = true;
    private final Logger logger = LoggerFactory.getLogger(MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        stageHandler=new StageHandler(this);
        stageHandler.updateStagesFromServerIfNeeded();
        if (FirstTimeInMainActivity)
        {
            FirstTimeInMainActivity = false;
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>()
                    {
                        @Override
                        public void onSuccess(LoginResult loginResult)
                        {
                            Toast.makeText(MainActivity.this, getString(R.string.facebookSuccess), Toast.LENGTH_LONG).show();
                            if (Profile.getCurrentProfile() != null)
                            {
                                stageHandler.newFacebookUser();
                            }
                        }
                        @Override
                        public void onCancel()
                        {

                        }
                        @Override
                        public void onError(FacebookException exception)
                        {
                            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends"));
        }

    }
    public void goToStageActivity(View view)
    {
        Stage thisStage = stageHandler.getStage(1);
        if (thisStage != null)
        {
            Intent intent = new Intent(MainActivity.this, StageActivity.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage(getString(R.string.noStages)).create().show();
        }
    }
    public void goToInstractions(View view)
    {
        Intent intent = new Intent(MainActivity.this, InstractionsActivity.class);
        startActivity(intent);
    }
    public void goToPreviousStagesActivity(View view)
    {
        Stage thisStage = stageHandler.getStage(1);
        if (thisStage != null)
        {
            Intent intent = new Intent(MainActivity.this, PreviousStagesActivity.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage(getString(R.string.noStages)).create().show();
        }
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
        return (accessToken != null);
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
