package a.atbash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Stage {
    private int number;
    private String question;
    private String clue;
    private String answer;
    public Stage(int number, String question, String clue, String answer)
    {
        this.number=number;
        this.question=question;
        this.clue=clue;
        this.answer=answer;
    }
}
