package a.atbash;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StageHandler
{
    private final Logger logger = LoggerFactory.getLogger(StageHandler.class);
    private StageDAL stageDAL;
    private final Context context;
    public StageHandler(Context context)
    {
        this.context = context;
        stageDAL = new StageDAL(context);
    }

    public Stage getStage(int numOfQuestion)
    {
        Stage s = null;
        try
        {
            s = stageDAL.getStage(numOfQuestion);
        }
        catch (IOException e)
        {
            logger.error("Exception in getting stage in StageHandler", e.toString());
        }
        return s;
    }

    public int getCurrentStageNumber()
    {
        int last = stageDAL.getCurrentStageNumber();
        return last;
    }

    public void setCurrentStageNumber (int curLevel)
    {
        int curLast = getCurrentStageNumber();
        if (curLast < curLevel) //i think this is not necessary. need to check.
        {
            stageDAL.setCurrentStageNumber (curLevel);
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
                    stages = objectMapper.readValue(new URL("http://192.168.9.22:8080/getAllStages"), new TypeReference<List<Stage>>(){});
                    System.out.println(stages);
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
        if (thisStage < getCount())
        {
            return getStage(thisStage + 1);
        }
        return null;
    }

    public int getCount()
    {
        return stageDAL.getCount();
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