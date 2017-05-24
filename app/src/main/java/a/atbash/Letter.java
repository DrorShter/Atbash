package a.atbash;
import android.widget.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
class Letter
{
    private String value; //value of letter
    private Button button; //button of letter
}
