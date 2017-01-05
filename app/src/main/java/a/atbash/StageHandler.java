package a.atbash;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.sql.SQLException;

public class StageHandler {
    private StageDAL stageDAL=new StageDAL();
    public Stage getStage(int numOfQuestion)
    {
        Cursor cur = stageDAL.queryForStage(numOfQuestion);
        cur.moveToFirst();
        int number=cur.getInt(cur.getColumnIndex("NumberOfQuestion"));
        String Answer=cur.getString((cur.getColumnIndex("Answer")));
        String Question=cur.getString(cur.getColumnIndex("Question"));
        String Hint=cur.getString(cur.getColumnIndex("Hint"));
        Stage s=new Stage(number,Question,Hint,Answer);
        return s;
    }
    public int getLastLevel()
    {
        Cursor cur = stageDAL.queryCurrentLevel();
        int last=cur.getInt(cur.getColumnIndex("last"));
        return last;
    }
    public void updateLastLevel(int curLevel)
    {
        int curLast=getLastLevel();
        if(curLast<curLevel)
        {
            stageDAL.queryForUpdateLast(curLevel);
        }
    }
}
