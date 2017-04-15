package a.atbash;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookUser
{
    private String facebookID;
    private String name;
    private int currentStageNumber;
    public FacebookUser(String facebookID, String name, int currentStageNumber) //TODO:LOMBOK
    {
        this.facebookID=facebookID;
        this.name=name;
        this.currentStageNumber=currentStageNumber;
    }
    public FacebookUser()
    {

    }

    public String getFacebookID() {
        return this.facebookID;
    }

    public String getName() {
        return this.name;
    }

    public int getCurrentStageNumber() {
        return this.currentStageNumber;
    }
}
