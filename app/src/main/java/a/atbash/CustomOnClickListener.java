package a.atbash;
import android.view.View;
import android.widget.EditText;
public class CustomOnClickListener implements View.OnClickListener
{
    private Letter letter;
    private EditText editText;
    public CustomOnClickListener(Letter letter, EditText editText)
    {
        this.letter = letter;
        this.editText = editText;
    }
    @Override
    public void onClick(View v)
    {
        editText.setText(editText.getText().insert(editText.getText().length(), letter.getValue()));
    }
}