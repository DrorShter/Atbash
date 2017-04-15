package a.atbash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class FacebookUser
{
    private String facebookID;
    private String name;
    private int currentStageNumber;
    FacebookUser(String facebookID, String name, int currentStageNumber) //TODO:LOMBOK
    {
        this.facebookID=facebookID;
        this.name=name;
        this.currentStageNumber=currentStageNumber;
    }
    FacebookUser()
    {

    }
}
