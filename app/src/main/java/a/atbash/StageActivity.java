package a.atbash;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageActivity extends AppCompatActivity {

    private Stage thisStage = new Stage(); //member
    private EditText editText; //member
    private TextView stageNumberTextView, questionTextView; //member
    private StageHandler stageHandler; //member
    private String messageToShare = ""; //member
    private static final int SUCCESS = 0; //member
    private static final int NO_MORE_STAGES = 1; //member

    //This function gets Bundle and returns void
    //This function is onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        stageHandler = new StageHandler(this);
        int stageNumber = intent.getIntExtra("Stage", stageHandler.getCurrentStageNumber());//get the number of stage from previous stages activity or default - database
        stageNumberTextView = (TextView) findViewById(R.id.textViewStageNumber);
        editText =(EditText)findViewById(R.id.answer);
        questionTextView=(TextView)findViewById(R.id.question);
        Button backspace = (Button)findViewById((R.id.n29));
        Button space = (Button)findViewById((R.id.n30));
        Button check = (Button)findViewById((R.id.checkButton));
        Button clue = (Button)findViewById((R.id.clueButton));
        thisStage = stageHandler.getStage(stageNumber);
        questionTextView.setText(thisStage.getQuestion());
        stageNumberTextView.setText(getString(R.string.stage) + " " + String.valueOf(thisStage.getNumber()));
        List<Letter> letters = new ArrayList<>();
        letters.add(new Letter(getString(R.string.kuf), (Button)findViewById((R.id.n1))));
        letters.add(new Letter(getString(R.string.reish), (Button)findViewById((R.id.n2))));
        letters.add(new Letter(getString(R.string.alef), (Button)findViewById((R.id.n3))));
        letters.add(new Letter(getString(R.string.tet), (Button)findViewById((R.id.n4))));
        letters.add(new Letter(getString(R.string.vav), (Button)findViewById((R.id.n5))));;
        letters.add(new Letter(getString(R.string.nunSofit), (Button)findViewById((R.id.n6))));
        letters.add(new Letter(getString(R.string.memSofit), (Button)findViewById((R.id.n7))));
        letters.add(new Letter(getString(R.string.pei), (Button)findViewById((R.id.n8))));
        letters.add(new Letter(getString(R.string.shin), (Button)findViewById((R.id.n9))));
        letters.add(new Letter(getString(R.string.dalet), (Button)findViewById((R.id.n10))));
        letters.add(new Letter(getString(R.string.gimel), (Button)findViewById((R.id.n11))));
        letters.add(new Letter(getString(R.string.caf), (Button)findViewById((R.id.n12))));
        letters.add(new Letter(getString(R.string.ain), (Button)findViewById((R.id.n13))));
        letters.add(new Letter(getString(R.string.yud), (Button)findViewById((R.id.n14))));
        letters.add(new Letter(getString(R.string.het), (Button)findViewById((R.id.n15))));
        letters.add(new Letter(getString(R.string.lamed), (Button)findViewById((R.id.n16))));
        letters.add(new Letter(getString(R.string.zain), (Button)findViewById((R.id.n17))));
        letters.add(new Letter(getString(R.string.samech), (Button)findViewById((R.id.n18))));
        letters.add(new Letter(getString(R.string.bet), (Button)findViewById((R.id.n19))));
        letters.add(new Letter(getString(R.string.hei), (Button)findViewById((R.id.n20))));
        letters.add(new Letter(getString(R.string.nun), (Button)findViewById((R.id.n21))));
        letters.add(new Letter(getString(R.string.mem), (Button)findViewById((R.id.n22))));
        letters.add(new Letter(getString(R.string.tzadick), (Button)findViewById((R.id.n23))));
        letters.add(new Letter(getString(R.string.taf), (Button)findViewById((R.id.n24))));
        letters.add(new Letter(getString(R.string.cafSofit), (Button)findViewById((R.id.n25))));
        letters.add(new Letter(getString(R.string.tzadickSofit), (Button)findViewById((R.id.n26))));
        letters.add(new Letter(getString(R.string.peiSofit), (Button)findViewById((R.id.n27))));
        letters.add(new Letter(".", (Button)findViewById((R.id.n28))));
        for (int i=0;i<letters.size();i++) //set CustomOnClickListener to all letters
        {
            Letter letter = letters.get(i);
            letter.getButton().setOnClickListener(new CustomOnClickListener(letter, editText){});
        }
        backspace.setOnClickListener(new View.OnClickListener()
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
        space.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().insert(editText.getText().length(), " "));
            }
        });
        check.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((editText.getText().toString()).equals(thisStage.getAnswer())) //if success
                {
                    if (stageHandler.getCurrentStageNumber() == thisStage.getNumber())
                    {
                        stageHandler.setCurrentStageNumber(thisStage.getNumber() + 1);
                    }
                    Stage temp  = stageHandler.getNextStage(thisStage.getNumber());
                    if (temp != null)
                    {
                        dialogManager(SUCCESS, temp); //pop-up
                        thisStage = temp;
                        questionTextView.setText(thisStage.getQuestion());
                        stageNumberTextView.setText(getString(R.string.stage) + " " + String.valueOf(thisStage.getNumber()));
                        editText.setText("");
                    }
                    else
                    {
                        dialogManager(NO_MORE_STAGES, null); //pop-up
                    }
                }
                else //if wrong
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(StageActivity.this).setMessage(getString(R.string.wrong));
                    final AlertDialog wrongAnswer = builder.show();
                    new android.os.CountDownTimer(1500, 1500) //set time for showing pop-up
                    {
                        public void onTick(long millisUntilFinished)
                        {

                        }
                        public void onFinish()
                        {
                            wrongAnswer.dismiss();
                        }
                    }.start();
                }
            }
        });
        clue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StageActivity.this);
                alertDialog.setMessage(thisStage.getClue()).create().show();
            }
        });
    }

    //This function gets View and returns void
    //This function moves player to MainActivity
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(StageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //This function gets int, Stage and returns void
    //This function responsible on pop-ups
    public void dialogManager(int option, Stage temp)
    {
        String alertText = "", positiveButtonText = "", negativeButtonText = "";
        DialogInterface.OnClickListener positiveDialogInterface = null, negativeDialogInterface = null;
        switch (option)
        {
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
                break;
        }
        negativeDialogInterface = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentTitle(getString(R.string.shareTitle))
                        .setContentDescription("Final Project Magshimim - Noam Bar Shlomo & Dror Shter")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                ShareDialog shareDialog = new ShareDialog(StageActivity.this);
                shareDialog.show(content);
            }
        };
        showDialog(alertText, positiveButtonText, negativeButtonText, positiveDialogInterface, negativeDialogInterface);
    }

    //This function gets String,String,String,DialogInterface.OnClickListener,DialogInterface.OnClickListener and returns void
    //This function responsible on pop-ups
    private void showDialog(String alertText, String positiveButtonText, String negativeButtonText,DialogInterface.OnClickListener positiveDialogInterface, DialogInterface.OnClickListener negativeDialogInterface)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StageActivity.this);
        alertDialog.setMessage(alertText).setPositiveButton(positiveButtonText, positiveDialogInterface).setNegativeButton(negativeButtonText, negativeDialogInterface).create().show();
    }

}
