package a.atbash;
//TODO: LOMBOK
import android.widget.Button;
public class Letter
{
    private String value;
    private Button button;
    public Letter(String value, Button b)
    {
        this.value = value;
        this.button = b;
    }
    public String getValue()
    {
        return value;
    }
    public Button getButton()
    {
        return button;
    }
}
