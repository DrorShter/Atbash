package a.atbash;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StageHandler {
    public StageHandler() throws SQLException, ClassNotFoundException{
    }

    private StageDAL stageDAL = new StageDAL();

    public Stage getStage(int numOfQuestion) throws SQLException {
        Stage s = stageDAL.getStage(numOfQuestion);
        return s;
    }

    public int getLastLevel() {
        int last = 0;
        try {
            last = stageDAL.getCurrentLevel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last;
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
}