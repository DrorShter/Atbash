package a.atbash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class StageActivity extends AppCompatActivity {
    // DataBase dataBase=new DataBase(this);
    Stage thisStage = new Stage();
    EditText editText;
    EditText editCheckText;
    EditText editTextClue;
    EditText editTextQ;
    Button bC;
    Button bClue;
    Button back;
    StageHandler stageHandler;
    GridLayout gridLayout;
    FacebookCallback facebookCallback;
    private PopupWindow popUpWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private String messageToShare = "";
    private int stageNumber;
    private final int SUCCESS = 0;
    private final int NO_MORE_STAGES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        stageHandler = new StageHandler();
        stageNumber = i.getIntExtra("Stage", stageHandler.getLastLevel()); //get the number of stage from previous stages activity or default - database
        editText = (EditText)findViewById(R.id.editText);
        editCheckText =(EditText)findViewById(R.id.checkAns);
        editTextClue=(EditText)findViewById(R.id.clue);
        editTextQ=(EditText)findViewById(R.id.question);
        back = (Button)findViewById(R.id.BACK);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        thisStage = stageHandler.getStage(stageNumber);
        Button[] b = new Button[30];
        b[0] = (Button)findViewById((R.id.n1));
        b[1] = (Button)findViewById((R.id.n2));
        b[2] = (Button)findViewById((R.id.n3));
        b[3] = (Button)findViewById((R.id.n4));
        b[4] = (Button)findViewById((R.id.n5));
        b[5] = (Button)findViewById((R.id.n6));
        b[6] = (Button)findViewById((R.id.n7));
        b[7] = (Button)findViewById((R.id.n8));
        b[8] = (Button)findViewById((R.id.n9));
        b[9] = (Button)findViewById((R.id.n10));
        b[10] = (Button)findViewById((R.id.n11));
        b[11] = (Button)findViewById((R.id.n12));
        b[12] = (Button)findViewById((R.id.n13));
        b[13] = (Button)findViewById((R.id.n14));
        b[14] = (Button)findViewById((R.id.n15));
        b[15] = (Button)findViewById((R.id.n16));
        b[16] = (Button)findViewById((R.id.n17));
        b[17] = (Button)findViewById((R.id.n18));
        b[18] = (Button)findViewById((R.id.n19));
        b[19] = (Button)findViewById((R.id.n20));
        b[20] = (Button)findViewById((R.id.n21));
        b[21] = (Button)findViewById((R.id.n22));
        b[22] = (Button)findViewById((R.id.n23));
        b[23] = (Button)findViewById((R.id.n24));
        b[24] = (Button)findViewById((R.id.n25));
        b[25] = (Button)findViewById((R.id.n26));
        b[26] = (Button)findViewById((R.id.n27));
        b[27] = (Button)findViewById((R.id.n28));
        b[28] = (Button)findViewById((R.id.n29));
        b[29] = (Button)findViewById((R.id.n30));
        bC = (Button)findViewById((R.id.checkButton));
        bClue = (Button)findViewById((R.id.clueButton));
        editTextQ.setText(thisStage.getQuestion());
        b[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.kuf)));
            }
        });
        b[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.reish)));
            }
        });
        b[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.alef)));
            }
        });
        b[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.tet)));
            }
        });
        b[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.vav)));
            }
        });
        b[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.nunSofit)));
            }
        });
        b[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.memSofit)));
            }
        });
        b[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.pei)));
            }
        });
        b[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.shin)));
            }
        });
        b[9].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.dalet)));
            }
        });
        b[10].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.gimel)));
            }
        });
        b[11].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.caf)));
            }
        });
        b[12].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.ain)));
            }
        });
        b[13].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.yud)));
            }
        });
        b[14].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.het)));
            }
        });
        b[15].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.lamed)));
            }
        });
        b[16].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.zain)));
            }
        });
        b[17].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.samech)));
            }
        });
        b[18].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.bet)));
            }
        });
        b[19].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.hei)));
            }
        });
        b[20].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.nun)));
            }
        });
        b[21].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.mem)));
            }
        });
        b[22].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.tzadick)));
            }
        });
        b[23].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.taf)));
            }
        });
        b[24].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.cafSofit)));
            }
        });
        b[25].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.tzadickSofit)));
            }
        });
        b[26].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), getString(R.string.peiSofit)));
            }
        });
        b[27].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "."));
            }
        });
        b[28].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!editText.getText().toString().equals(""))
                {
                    editText.setText(editText.getText().delete(editText.getText().length() - 1, editText.getText().length()));
                }
            }
        });
        b[29].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().insert(editText.getText().length(), " "));
            }
        });
        bC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((editText.getText().toString()).equals(thisStage.getAnswer()))
                {
                    if (stageHandler.getLastLevel() == thisStage.getNumber())
                    {
                        stageHandler.updateLastLevel(thisStage.getNumber());
                        Bundle param = new Bundle();
                        param.putInt("score", 11000);
                    }
                    Stage temp  = stageHandler.getNextStage(thisStage.getNumber());
                    if (temp != null)
                    {
                        dialogManager(SUCCESS, temp);
                        thisStage = temp;
                        editTextQ.setText(thisStage.getQuestion());
                        editTextClue.setText("");
                        editCheckText.setText("");
                        editText.setText("");
                    }
                    else
                    {
                        dialogManager(NO_MORE_STAGES, null);
                    }
                }
                else
                {
                    new android.os.CountDownTimer(1500, 1000)
                    {
                        public void onTick(long millisUntilFinished)
                        {
                            editCheckText.setText(R.string.wrong);
                        }
                        public void onFinish() {
                            editCheckText.setText("");
                        }
                    }.start();
                }
            }
        });
        bClue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editTextClue.setText(thisStage.getClue());
            }
        });
        facebookCallback = new FacebookCallback() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(StageActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void dialogManager(int option, final Stage temp)
    {
        String alertText = "", positiveButtonText = "", negativeButtonText = "";
        DialogInterface.OnClickListener positiveDialogInterface = null, negativeDialogInterface = null;
        switch (option) {
            case SUCCESS:
                Random rand = new Random();
                int n = rand.nextInt(3) + 1;
                switch (n)
                {
                    case 1:
                        alertText = getString(R.string.successInStage1);
                        break;
                    case 2:
                        alertText = getString(R.string.successInStage2);
                        break;
                    case 3:
                        alertText = getString(R.string.successInStage3);
                        break;
                }
                positiveButtonText = getString(R.string.nextStage);
                negativeButtonText = getString(R.string.shareInFacebook);
                messageToShare = getString(R.string.shareMessage1) + " " + Integer.toString(thisStage.getNumber()) +  getString(R.string.shareMessage2);
                //no positiveDialogInterface because it moves to next stage automatically

                break;
            case NO_MORE_STAGES:
                alertText = getString(R.string.finsihedAllLevels);
                positiveButtonText = getString(R.string.backToMainMenuAfterFinishAllLevels);
                negativeButtonText = getString(R.string.shareInFacebook);
                messageToShare = getString(R.string.shareMessageFinish) +  getString(R.string.shareMessage2);
                positiveDialogInterface = new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent intent = new Intent(StageActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
        }
        negativeDialogInterface = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentTitle(getString(R.string.shareTitle))
                        .setContentDescription("Final Project Magshimim - Noam Bar Shlomo & Dror Shter")
                        .setQuote(messageToShare)
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                ShareDialog shareDialog = new ShareDialog(StageActivity.this);
                shareDialog.show(content);
            }
        };
        showDialog(alertText, positiveButtonText, negativeButtonText, positiveDialogInterface, negativeDialogInterface);
    }
    private void showDialog(String alertText, String positiveButtonText, String negativeButtonText,DialogInterface.OnClickListener positiveDialogInterface, DialogInterface.OnClickListener negativeDialogInterface)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StageActivity.this);
        alertDialog.setMessage(alertText).setPositiveButton(positiveButtonText, positiveDialogInterface).setNegativeButton(negativeButtonText, negativeDialogInterface).create().show();
    }

}
