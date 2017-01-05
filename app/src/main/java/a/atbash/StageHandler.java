package a.atbash;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.sql.SQLException;

public class StageHandler {
    public Stage getStage(int numOfQuestion)
    {
        StageDAL stageDAL=new StageDAL();
        Cursor cur= stageDAL.queryForStage(1);
        cur.moveToFirst();
        int number=cur.getInt(cur.getColumnIndex("NumberOfQuestion"));
        System.out.println("number = " + number);
        String Answer=cur.getString((cur.getColumnIndex("Answer")));
        System.out.println("Answer = " + Answer);
        String Question=cur.getString(cur.getColumnIndex("Question"));
        String Hint=cur.getString(cur.getColumnIndex("Hint"));
        Stage s=new Stage(number,Question,Hint,Answer);
        return s;
    }
}
