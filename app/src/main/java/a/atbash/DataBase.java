package a.atbash;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
@lombok.Getter
@lombok.Setter
public class DataBase extends SQLiteOpenHelper
{

    private static String DB_PATH = "/data/data/a.atbash/databases/"; //The Android's default system path of your application database.
    private static String DB_NAME = "AtbashClient";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    //constructor
    public DataBase(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }
    //create empty database
    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();
        if(dbExist)
        {
            //do nothing - database already exist
        }
        else
        {
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }
    }
    //check if database already exist
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try
        {
            String myPath = DB_PATH + DB_NAME;
            System.out.println(myPath);
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException e)
        {
            //database does't exist yet.
        }
        if(checkDB != null)
        {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    //copy database from assets to local database
    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0)
        {
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //open database
    public void openDataBase() throws SQLException
    {
        String myPath = DB_PATH + DB_NAME;
        System.out.println(myPath);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    //close database
    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor rawQuery(String query)
    {
        Cursor cur= myDataBase.rawQuery(query, null);
        return cur;
    }
}
