package a.atbash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class StageHandler{
    private Context context;
    private StageDAL stageDAL = null;
    private SQLiteDatabase.CursorFactory factory;
    public StageHandler(Context context) {
        this.context=context;
        stageDAL=new StageDAL(this.context);
    }

    public Stage getStage(int numOfQuestion) throws SQLException {
        Stage s = null;
        try {
            s = stageDAL.getStage(numOfQuestion);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public int getLastLevel() {
        int last;
        last = stageDAL.getCurrentLevel();
        return last;
    }
        //return last;
         // just for check, should return last

    public void updateLastLevel(int curLevel) throws SQLException {
        int curLast = getLastLevel();
        if (curLast < curLevel) {
            stageDAL.updateLastLevel(curLevel);
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
                    List<Stage> stages = objectMapper.readValue(new URL("http://192.168.1.13:8080/useraccount/AtbashServerAPI/getAllStages"), new TypeReference<List<Stage>>(){});
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

    public Stage getNextStage(int thisStage)
    {
        if (thisStage < getLastLevel())
        {
            try {
                return getStage(thisStage + 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}