package a.atbash;

import android.content.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class StageDAL
{
    private final Logger logger = LoggerFactory.getLogger(StageDAL.class);
    private ObjectMapper mapper=null;
    private File path;
    StageDAL(Context context)
    {
        path=new File(context.getFilesDir(), "stages");
        mapper=new ObjectMapper();
        if(!path.exists())
        {
            path.mkdirs();
        }
    }
    Stage getStage(int num) throws IOException
    {
        ObjectMapper mapper=new ObjectMapper();
        File file = new File(path, num + ".json");
        return mapper.readValue(file, Stage.class);
    }
    public int getCurrentStageNumber()
    {
        File file = new File(path, "currentLevel.txt");
        String s1 = "";
        if(!file.exists())
        {
            file.getParentFile().mkdirs();
            FileOutputStream stream = null;
            try
            {
                stream = new FileOutputStream(file);
                stream.write("1".getBytes());
                stream.close();
            }
            catch (IOException e)
            {
                logger.error("Exception 1 in getting CurrentLevel in StageDAL", e.toString());
            }
            return 1;
        }
        else
        {
            Scanner scanner= null;
            try
            {
                scanner = new Scanner(file);
            }
            catch (FileNotFoundException e)
            {
                logger.error("Exception 2 in getting CurrentLevel in StageDAL", e.toString());
            }
            if (scanner != null)
            {
                s1=scanner.nextLine();
            }
            System.out.println(s1);
            return Integer.parseInt(s1);
        }

    }
    public void setCurrentStageNumber(int cur)
    {
        File file = new File(path, "currentLevel.txt");
        try
        {
            int tmp= getCurrentStageNumber();
            if(tmp<cur)
            {
                FileOutputStream stream = new FileOutputStream(file, false);
                stream.write(String.valueOf(cur).getBytes());
                stream.close();
            }
        }
        catch (IOException e)
        {
            logger.error("Exception in setting new CurrentLevel in StageDAL", e.toString());
        }
    }

    public void updateStagesFromServer(List<Stage> stages) throws IOException
    {
        Stage stage=null;
        for (int i=0;i<stages.size()-1;i++)
        {
            stage = stages.get(i);
            File file = new File(path, stage.getNumber() + ".json");
            mapper.writeValue(file, stage);
        }
    }

    public int getCount()
    {
        return path.listFiles().length-1;
    }
}