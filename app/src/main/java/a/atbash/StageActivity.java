package a.atbash;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Dimension;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.HashMap;
import java.io.IOException;
import java.lang.Object;
import java.sql.SQLException;
import java.util.Map;

import static a.atbash.R.id.n1;

public class StageActivity extends AppCompatActivity implements View.OnClickListener {
    DataBase dataBase=new DataBase(this);
    Stage x;
    EditText editText;
    EditText editCheckText;
    EditText editTextClue;
    EditText editTextQ;
    Button[] b = new Button[30];
    Button bC;
    Button bClue;
    Button back;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StageHandler stageHandler= new StageHandler();
        editText = (EditText)findViewById(R.id.editText);
        editCheckText =(EditText)findViewById(R.id.checkAns);
        editTextClue=(EditText)findViewById(R.id.clue);
        editTextQ=(EditText)findViewById(R.id.question);
        back = (Button)findViewById(R.id.BACK);
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
        System.out.println("good");
        final Stage x = stageHandler.getStage(1);
        System.out.println("good2");
        editTextQ.setText(x.getQuestion());
        int i;
        for (i=0;i<30;i++)
        {
            b[i].setOnClickListener(this);
        }
        bC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((editText.getText().toString()).equals(x.getAnswer()))
                {
                    editCheckText.setText("נכון מאוד!");
                }
                else {

                    new android.os.CountDownTimer(1500, 1000) {

                        public void onTick(long millisUntilFinished) {
                            editCheckText.setText("לא נכון, נסה שנית");
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
                editTextClue.setText(x.getClue());
            }
        });}
        public void onClick(View v)
        {
            editText.setText(editText.getText().insert(editText.getText().length(), "ק"));
        }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(StageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

