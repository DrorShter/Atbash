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
    private String facebookID;
    private String name;
    private int currentStageNumber;
}
