package a.atbash;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
@lombok.Getter
@lombok.Setter
public class StageDAL extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/a.atbash/databases/"; //The Android's default system path of your application database.
    private static String DB_NAME = "AtbashClient";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    //constructor
    public StageDAL(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        try {
            copyDataBase();
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //copy database from assets to local database
    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //open database
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        System.out.println(myPath);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    //close database
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int getCurrentLevel() throws SQLException {
        String query = "SELECT * FROM lastLevel";
        Cursor cur = myDataBase.rawQuery(query, null);
        String last = cur.getString(cur.getColumnIndex("last"));
        int lastNum = Integer.getInteger(last);
        return lastNum;
    }


    public Stage getStage(int num) throws SQLException {
        String question, answer, clue, query = "SELECT * FROM Level WHERE NumberOfQuestion=" + num;
        Cursor cur = myDataBase.rawQuery(query, null);
        clue = cur.getString(cur.getColumnIndex("clue"));
        question = cur.getString(cur.getColumnIndex("Question"));
        answer = cur.getString(cur.getColumnIndex("Answer"));
        Stage s = new Stage(num, question, clue, answer);
        return s;
    }
}