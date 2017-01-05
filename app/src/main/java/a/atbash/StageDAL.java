package a.atbash;

import android.database.Cursor;

import java.sql.SQLException;

public class StageDAL
{
    private DataBase db;
    public StageDAL()
    {
        DataBase db=new DataBase(null);
        try
        {
            db.openDataBase();
        }
        catch(SQLException blah)
        {
            //stam
        }
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
}
