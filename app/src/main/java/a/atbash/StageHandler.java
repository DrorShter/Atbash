package a.atbash;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StageHandler
{
    private final Logger logger = LoggerFactory.getLogger(StageHandler.class);
    public StageHandler()
    {

    }
    private StageDAL stageDAL = new StageDAL();

    public Stage getStage(int numOfQuestion)
    {
        Stage s = stageDAL.getStage(numOfQuestion);
        return s;
    }

    public int getLastLevel()
    {
        int last = stageDAL.getCurrentLevel();
        return last;
    }

    public void updateLastLevel(int curLevel)
    {
        int curLast = getLastLevel();
        if (curLast < curLevel) //i think this is not necessary. need to check.
        {
            stageDAL.updateLastLevel(curLevel);
        }
    }

    public boolean updateStagesFromServer()
    {
        boolean ret = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Stage> stages = null;
                ObjectMapper objectMapper = new ObjectMapper();
                try
                {
                    stages = objectMapper.readValue(new URL("http://192.168.14.80:8080/useraccount/AtbashServerAPI/getAllStages"), new TypeReference<List<Stage>>(){});
                    stageDAL.updateStagesFromServer(stages);
                }
                catch (Exception e)
                {
                    logger.error("Exception in connection with RestAPI in StageHandler", e.toString());
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in StageHandler", e.toString());
            ret = false;
        }
        return ret;
    }

    public Stage getNextStage(int thisStage)
    {
        if (thisStage < getLastLevel())
        {
            return getStage(thisStage + 1);
        }
        return null;
    }

    public String[][] getNamesAndStagesFriends(String[] ids)
    {
        //TODO: should be from server
        String[][] ret = new String[][]{{"friend1", "15"}, {"b", "25"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}};
        return ret;
    }
    public String[][] getNamesAndStagesGlobal()
    {
        //TODO: should be from server
        String[][] ret = new String[][]{{"global1", "15"}, {"b", "25"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}, {"", "8"}};
        return ret;
    }
}