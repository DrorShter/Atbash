package a.atbash;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.*;

class DataBase {
    private SQLiteDatabase db;
    public DataBase(SQLiteDatabase db)
    {
        this.db=db;
    }
    public int getLast()
    {
        db.execSQL("create table if not exists sampletable(name text, location text");
        db.execSQL("insert into sampletable values('ran', 'hyderanad')");
        Cursor cursor=db.rawQuery("SELECT * FROM sampletable", null);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        System.out.println(name);
        int last = Integer.parseInt(cursor.getString(cursor.getColumnIndex("last")));
        System.out.println(last);
        return last;
    }
}
