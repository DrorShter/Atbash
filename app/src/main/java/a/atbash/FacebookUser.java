package a.atbash;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookUser
{
    private String FacebookID;
    private String name;
    private int CurrentStageNumber;
    public FacebookUser(String FacebookID, String name, int CurrentStageNumber) //TODO:LOMBOK
    {
        this.FacebookID=FacebookID;
        this.name=name;
        this.CurrentStageNumber=CurrentStageNumber;
    }
}
