package a.atbash;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
    private Connection connection = null;
    private Statement statement = null;

    public DataBase() throws SQLException, ClassNotFoundException {

        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        connection = DriverManager.getConnection("jdbc:sqldroid:" + "/data/data/a.atbash/AtbashClient1.db");
        //"jdbc:sqlite:/data/data/a.atbash/databases/AtbashClient1"
    }

    public ResultSet ex(String s) throws SQLException {
        ResultSet resultset= statement.executeQuery(s);
        System.out.println(resultset);
        return resultset;
    }


}
