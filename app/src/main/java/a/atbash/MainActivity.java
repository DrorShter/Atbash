package a.atbash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import static android.R.attr.content;

public class MainActivity extends AppCompatActivity {
    Button b;
    private StageHandler stageHandler;
    private static boolean alreadyOpened = false;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (!alreadyOpened)
        {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    });
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
            alreadyOpened = !alreadyOpened;
        }
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
    public void GoToPreviousStagesActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, PreviousStagesActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
