package a.atbash;

import android.database.Cursor;

import java.sql.SQLException;

public class StageDAL {
    public Cursor queryForStage(int num)
    {
        DataBase db=new DataBase(null);
        try {
            db.openDataBase();
        }
        catch(SQLException blah)
        {
            //stam
        }
        return db.rawQuery("SELECT * FROM Level where numberOfQuestion=="+num);
    }
}
