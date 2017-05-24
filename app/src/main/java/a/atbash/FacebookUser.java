package a.atbash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
class FacebookUser
{
    private String facebookID; //id
    private String name; //name
    private int currentStageNumber; //stage
}
