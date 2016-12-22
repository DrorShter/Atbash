package a.atbash;

import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editCheckText;
    EditText editTextClue;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;
    Button b11;
    Button b12;
    Button b13;
    Button b14;
    Button b15;
    Button b16;
    Button b17;
    Button b18;
    Button b19;
    Button b20;
    Button b21;
    Button b22;
    Button b23;
    Button b24;
    Button b25;
    Button b26;
    Button b27;
    Button b28;
    Button b29;
    Button b30;
    Button bC;
    Button bClue;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editCheckText =(EditText)findViewById(R.id.checkAns);
        editTextClue=(EditText)findViewById(R.id.clue);
        b1 = (Button)findViewById((R.id.n1));
        b2 = (Button)findViewById((R.id.n2));
        b3 = (Button)findViewById((R.id.n3));
        b4 = (Button)findViewById((R.id.n4));
        b5 = (Button)findViewById((R.id.n5));
        b6 = (Button)findViewById((R.id.n6));
        b7 = (Button)findViewById((R.id.n7));
        b8 = (Button)findViewById((R.id.n8));
        b9 = (Button)findViewById((R.id.n9));
        b10 = (Button)findViewById((R.id.n10));
        b11 = (Button)findViewById((R.id.n11));
        b12 = (Button)findViewById((R.id.n12));
        b13 = (Button)findViewById((R.id.n13));
        b14 = (Button)findViewById((R.id.n14));
        b15 = (Button)findViewById((R.id.n15));
        b16 = (Button)findViewById((R.id.n16));
        b17 = (Button)findViewById((R.id.n17));
        b18 = (Button)findViewById((R.id.n18));
        b19 = (Button)findViewById((R.id.n19));
        b20 = (Button)findViewById((R.id.n20));
        b21 = (Button)findViewById((R.id.n21));
        b22 = (Button)findViewById((R.id.n22));
        b23 = (Button)findViewById((R.id.n23));
        b24 = (Button)findViewById((R.id.n24));
        b25 = (Button)findViewById((R.id.n25));
        b26 = (Button)findViewById((R.id.n26));
        b27 = (Button)findViewById((R.id.n27));
        b28 = (Button)findViewById((R.id.n28));
        b29 = (Button)findViewById((R.id.n29));
        b30 = (Button)findViewById((R.id.n30));
        bC = (Button)findViewById((R.id.checkButton));
        bClue = (Button)findViewById((R.id.clueButton));

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ק"));
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ר"));
            }
        });
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "א"));
            }
        });
        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ט"));
            }
        });
        b5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ו"));
            }
        });
        b6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ן"));
            }
        });
        b7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ם"));
            }
        });
        b8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "פ"));
            }
        });
        b9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ש"));
            }
        });
        b10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ד"));
            }
        });
        b11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ג"));
            }
        });
        b12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "כ"));
            }
        });
        b13.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ע"));
            }
        });
        b14.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "י"));
            }
        });
        b15.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ח"));
            }
        });
        b16.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            editText.setText(editText.getText().insert(editText.getText().length(), "ל"));
        }
        });
        b17.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ז"));
            }
        });
        b18.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ס"));
            }
        });
        b19.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ב"));
            }
        });
        b20.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ה"));
            }
        });
        b21.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "נ"));
            }
        });
        b22.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "מ"));
            }
        });
        b23.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "צ"));
            }
        });
        b24.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ת"));
            }
        });
        b25.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ך"));
            }
        });
        b26.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ץ"));
            }
        });
        b27.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "ף"));
            }
        });
        b28.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), "."));
            }
        });
        b29.setOnClickListener(new View.OnClickListener()
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
        b30.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText.setText(editText.getText().insert(editText.getText().length(), " "));
            }
        });
        bC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((editText.getText().toString()).equals("מוחמד"))
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
                editTextClue.setText("שם ערבי נפוץ");
            }
        });
    }
}
