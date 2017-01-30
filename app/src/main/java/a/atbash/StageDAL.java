package a.atbash;

import android.app.Application;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StageDAL extends Application
{
    private Connection connection = null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement = null;

    private Connection getConnection() {
        Connection con=null;
        //Context context = getApplicationContext();
        String name = "AtbashClient2.db";
        String DB_PATH = "/data/user/0/a.atbash/files/";
        System.out.println(DB_PATH+name);
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqlite.JDBC").newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" +DB_PATH+ "/AtbashClient2.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public StageDAL() throws SQLException, ClassNotFoundException {
        connection=getConnection();
    }

    public int getCurrentLevel() throws SQLException {
        String query="SELECT * FROM lastLevel";
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        int last=Integer.parseInt(resultSet.getString("last"));
        return last;
    }
    public Stage getStage(int num) throws SQLException {
        String question, answer, clue, query="SELECT * FROM Level WHERE NumberOfQuestion=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, num);
        resultSet= preparedStatement.executeQuery();
        question=resultSet.getString("Question");
        answer=resultSet.getString("Answer");
        clue=resultSet.getString("Clue");
        return new Stage(num, question, clue, answer);
    }
    public void updateLastLevel(int curLevel) throws SQLException {
        String query="UPDATE lastLevel set last=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, curLevel);
        preparedStatement.executeQuery();
    }
}
