package a.atbash;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
class Stage {
    private int number; //id
    private String question; //question
    private String clue; //clue for question
    private String answer; //answer for question
}
