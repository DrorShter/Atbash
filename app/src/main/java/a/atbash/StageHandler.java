package a.atbash;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class StageHandler {
    public StageHandler()
    {

    }
    private ForServer stageDAL = new ForServer();

    public Stage getStage(int numOfQuestion) throws SQLException {
        Stage s = stageDAL.getStage(numOfQuestion);
        return s;
    }

    public int getLastLevel() {
        int last = 3;
        try {
            last = stageDAL.getCurrentLevel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return last;
        return 3; // just for check, should return last
    }

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