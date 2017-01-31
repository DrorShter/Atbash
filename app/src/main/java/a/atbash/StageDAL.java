package a.atbash;

import android.app.Application;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StageDAL extends Application
{
    private Connection connection = null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement = null;

    private Connection getConnection() {
        Connection con=null;
        //Context context = getApplicationContext();
        /*
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
        */
        return con;
    }
    public StageDAL()
    {
        connection=getConnection();
    }

    public int getCurrentLevel() throws SQLException {
        /*
        String query="SELECT * FROM lastLevel";
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        int last=Integer.parseInt(resultSet.getString("last"));
        return last;
        */
        return 0; //just for not exception - noam
    }
    public Stage getStage(int num) throws SQLException {
        /*
        String question, answer, clue, query="SELECT * FROM Level WHERE NumberOfQuestion=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, num);
        resultSet= preparedStatement.executeQuery();
        question=resultSet.getString("Question");
        answer=resultSet.getString("Answer");
        clue=resultSet.getString("Clue");
        return new Stage(num, question, clue, answer);
        */
        return new Stage(1, "א", "ב", "ג"); //just for not compilation error - noam
    }
    public void updateLastLevel(int curLevel) throws SQLException {
        /*
        String query="UPDATE lastLevel set last=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, curLevel);
        preparedStatement.executeQuery();
        */
    }
    public void updateStagesFromServer(List <Stage> list)
    {
        System.out.println("from server");
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
