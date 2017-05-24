package a.atbash;

import android.view.View;
import android.widget.EditText;

class CustomOnClickListener implements View.OnClickListener
{
    private Letter letter; //letter
    private EditText editText; //editText
    //constructor
    CustomOnClickListener(Letter letter, EditText editText)
    {
        this.letter = letter;
        this.editText = editText;
    }
    //This function gets View and returns void
    //It set what is going on while clicking button
    @Override
    public void onClick(View v)
    {
        editText.setText(editText.getText().insert(editText.getText().length(), letter.getValue()));
    }
}