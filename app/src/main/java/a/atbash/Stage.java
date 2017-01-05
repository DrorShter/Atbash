package a.atbash;

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
    public String getQuestion()
    {
        return this.question;
    }
    public String getAnswer()
    {
        return this.answer;
    }
    public String getClue()
    {
        return this.clue;
    }
    public int getNumber(){return this.number;}
}
