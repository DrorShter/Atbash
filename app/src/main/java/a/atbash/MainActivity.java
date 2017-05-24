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
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button b; //declare
    private StageHandler stageHandler; //member
    private static CallbackManager callbackManager; //member
    private static boolean FirstTimeInMainActivity = true; //member
    private final Logger logger = LoggerFactory.getLogger(MainActivity.class); //set logger
    //This function gets Bundle and returns void
    //This function is onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FacebookSdk.sdkInitialize(getApplicationContext());
        System.out.println(FacebookSdk.getApplicationSignature(getApplicationContext()));
        stageHandler=new StageHandler(this);
        stageHandler.updateStagesFromServerIfNeeded();
        if (FirstTimeInMainActivity)
        {
            FirstTimeInMainActivity = false;
            callbackManager = CallbackManager.Factory.create();
            //facebook connecting
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>()
                    {
                        @Override
                        public void onSuccess(LoginResult loginResult) //if success
                        {
                            Toast.makeText(MainActivity.this, getString(R.string.facebookSuccess), Toast.LENGTH_LONG).show();
                            if (Profile.getCurrentProfile() != null)
                            {
                                stageHandler.newFacebookUser();
                            }
                        }
                        @Override
                        public void onCancel() //if cancel
                        {

                        }
                        @Override
                        public void onError(FacebookException exception) //if error
                        {
                            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends")); //ask for permissions
        }

    }

    //This function gets View and returns void
    //This function moves the player to StageActivity
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

    //This function gets View and returns void
    //This function moves the player to InstructionsActivity
    public void goToInstractions(View view)
    {
        Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
        startActivity(intent);
    }

    //This function gets View and returns void
    //This function moves the player to PreviousStagesActivity
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

    //This function gets View and returns void
    //This function moves the player to LeaderboardsActivity
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

    //This function gets void and returns boolean
    //This function checks if user is connected with facebook
    public boolean isLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null);
    }

    //This function gets final int, final int, final Intent and returns void
    //This function is the onActivityResult function
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
