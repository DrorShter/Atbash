package a.atbash;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

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
    public boolean updateStagesFromServer()
    {
        boolean ret = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Stage> stages = objectMapper.readValue(new URL("http://192.168.1.11:8080/useraccount/AtbashServerAPI/getAllStages"), new TypeReference<List<Stage>>(){});
                    stageDAL.updateStagesFromServer(stages);
                }
                catch (Exception e)
                {
                    System.out.println("catch in connection with RestAPI");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException i)
        {
            System.out.println("error in joining the thread");
            ret = false;
        }
        return ret;
    }
}