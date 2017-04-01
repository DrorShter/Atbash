package a.atbash;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StageDAL
{
    private ObjectMapper mapper=null;
    private File path;
    public StageDAL(Context context)
    {
        //path=new File(context.getFilesDir(), "stages");
        //mapper=new ObjectMapper();
    }

    public Stage getStage(int num) throws IOException
    {
        /*
        ObjectMapper mapper=new ObjectMapper();
        File file = new File(path, num + ".json");
        return mapper.readValue(file, Stage.class);
        */
        Stage stage = new Stage(3, "ק", "ר", "ש"); //debug
        return stage; //debug
    }
    public int getCurrentLevel() {
        /*
        File file = new File(path, "currentLevel.txt");
        if(!file.exists())
        {
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                stream.write("1".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }
        else
        {
            Scanner scanner= null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String s=scanner.nextLine();
            return Integer.parseInt(s);
        }
        */
        return 3; //just for debug
    }
    public void updateLastLevel(int cur)
    {
        /*
        File file = new File(path, "currentLevel.txt");
        try {
            int tmp= getCurrentLevel();
            if(tmp<cur)
            {
                FileOutputStream stream = new FileOutputStream(file, false);
                stream.write(cur);
                stream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void updateStagesFromServer(List<Stage> stages) throws IOException {
        /*for (Stage stage : stages) {
            File file = new File(path, stage.getNumber() + ".json");
            mapper.writeValue(file, stage);
        }
        */
    }
}