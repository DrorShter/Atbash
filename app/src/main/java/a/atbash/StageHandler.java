package a.atbash;

import java.sql.SQLException;

public class StageHandler {
    public StageHandler() throws SQLException, ClassNotFoundException {
    }

    private StageDAL stageDAL=new StageDAL();

    public Stage getStage(int numOfQuestion) throws SQLException {
        Stage s=stageDAL.getStage(numOfQuestion);
        return s;
    }
    public int getLastLevel(){
        int last= 0;
        try {
            last = stageDAL.getCurrentLevel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last;
    }
    public void updateLastLevel(int curLevel) throws SQLException {
        int curLast=getLastLevel();
        if(curLast<curLevel)
        {
            stageDAL.updateLastLevel(curLevel);
        }
    }
}
