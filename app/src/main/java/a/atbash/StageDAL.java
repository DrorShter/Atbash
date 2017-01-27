package a.atbash;

import android.database.Cursor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StageDAL
{
    private DataBase db;
    public StageDAL()
    {
        /*
        DataBase db=new DataBase(null);
        try
        {
            db.createDataBase();
        }
        catch(IOException ieo)
        {

        }
        try
        {
            db.openDataBase();
        }
        catch(SQLException e)
        {
            throw new Error("Failed in opening database");
        }
        */
    }
    public Cursor queryCurrentLevel()
    {
        return db.rawQuery("SELECT * FROM lastLevel");
    }
    public Cursor queryForStage(int num)
    {
        return db.rawQuery("SELECT * FROM Level where numberOfQuestion=="+num);
    }
    public void queryForUpdateLast(int curLevel)
    {
        db.rawQuery("UPDATE lastLevel set last="+curLevel);
    }
    public void updateStagesFromServer(List<Stage> list)
    {
        System.out.println(list.get(0).getNumber());
        System.out.println(list.get(0).getQuestion());
        System.out.println(list.get(0).getClue());
        System.out.println(list.get(0).getAnswer());
        System.out.println(list.get(1).getNumber());
        System.out.println(list.get(1).getQuestion());
        System.out.println(list.get(1).getClue());
        System.out.println(list.get(1).getAnswer());
        System.out.println(list.get(2).getNumber());
        System.out.println(list.get(2).getClue());
        System.out.println(list.get(2).getQuestion());
        System.out.println(list.get(2).getAnswer());
        //work for dror
    }
}