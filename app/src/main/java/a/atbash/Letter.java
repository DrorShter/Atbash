package a.atbash;
//TODO: LOMBOK
import android.widget.Button;

class Letter
{
    private String value;
    private Button button;
    Letter(String value, Button b)
    {
        this.value = value;
        this.button = b;
    }
    String getValue()
    {
        return value;
    }
    public Button getButton()
    {
        return button;
    }
}
