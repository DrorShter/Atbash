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
    public Stage(){}
    public Stage(int number, String question, String clue, String answer)
    {
        this.number=number;
        this.question=question;
        this.clue=clue;
        this.answer=answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int getNumber() {
        return this.number;
    }

    public String getClue() {
        return this.clue;
    }

    public String getQuestion() {
        return this.question;
    }
}
