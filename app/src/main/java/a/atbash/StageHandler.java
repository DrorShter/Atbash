package a.atbash;

import android.content.Context;
import android.hardware.camera2.params.Face;

import com.facebook.Profile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StageHandler
{
    private final Logger logger = LoggerFactory.getLogger(StageHandler.class);
    private StageDAL stageDAL;
    private final Context context;
    private String address;
    public StageHandler(Context context)
    {
        this.context = context;
        stageDAL = new StageDAL(context);
        final String IP = "192.168.9.17";
        final String port = "8080";
        address = "http://" + IP + ":" + port;
    }

    public Stage getStage(int numOfQuestion)
    {
        Stage s = null;
        try
        {
            s = stageDAL.getStage(numOfQuestion);
        }
        catch (IOException e)
        {
            logger.error("Exception in getting stage in getStage in StageHandler", e.toString());
        }
        return s;
    }

    public int getCurrentStageNumber()
    {
        int last = stageDAL.getCurrentStageNumber();
        return last;
    }

    public void setCurrentStageNumber (int curLevel)
    {
        int curLast = getCurrentStageNumber();
        if (curLast < curLevel) //TODO: i think this is not necessary. need to check.
        {
            stageDAL.setCurrentStageNumber (curLevel);
        }
    }

    public boolean updateStagesFromServer()
    {
        boolean ret = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Stage> stages = null;
                ObjectMapper objectMapper = new ObjectMapper();
                try
                {
                    stages = objectMapper.readValue(new URL(address + "/getAllStages"), new TypeReference<List<Stage>>(){});
                    System.out.println(stages);
                    stageDAL.updateStagesFromServer(stages);
                }
                catch (Exception e)
                {
                    logger.error("Exception in connection with RestAPI in updateStagesFromServer in StageHandler", e.toString());
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in updateStagesFromServer in StageHandler", e.toString());
            ret = false;
        }
        return ret;
    }

    public Stage getNextStage(int CurrentStageNumber)
    {
        if (CurrentStageNumber < getCount())
        {
            updateFacebookUser(CurrentStageNumber + 1);
            return getStage(CurrentStageNumber + 1);
        }
        return null;
    }

    public int getCount()
    {
        return stageDAL.getCount();
    }

    public void updateFacebookUser(int currentStageNumber)
    {
        final boolean success = false;
        final FacebookUser user = new FacebookUser(getFacebookID(), getFacebookName(), currentStageNumber);
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ObjectMapper objectMapper = new ObjectMapper();
                try
                {
                    String send = address + "/updateFacebookUser/" + user.getFacebookID() + "/" + String.valueOf(user.getCurrentStageNumber() + "/" + user.getName());
                    System.out.println(send);
                    FacebookUser userReturned = objectMapper.readValue(new URL(send), new TypeReference<FacebookUser>(){});
                    if (userReturned != user) //if there was error in the middle
                    {
                        throw new Exception();
                    }
                }
                catch (Exception e)
                {
                    logger.error("Exception in connection with RestAPI in updateFacebookUser in StageHandler", e.toString());
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in updateFacebookUser in StageHandler", e.toString());
        }
    }

    public void newFacebookUser()
    {
        updateFacebookUser(1);
    }

    public class getFacebookFriendsThread implements Runnable
    {
        private String[] ids;
        private List<FacebookUser> facebookUsers = null;
        public getFacebookFriendsThread(String[] ids)
        {
            this.ids = ids;
        }
        public void run()
        {

            ObjectMapper objectMapper = new ObjectMapper();
            try
            {
                facebookUsers = objectMapper.readValue(new URL(address + "/getFacebookFriends/" + ids.toString()), new TypeReference<List<FacebookUser>>(){});
            }
            catch (Exception e)
            {
                logger.error("Exception in connection with RestAPI in getFacebookFriendsThread in StageHandler", e.toString());
            }
        }
        public List<FacebookUser> getfacebookUsers()
        {
            return facebookUsers;
        }
    }

    public List<FacebookUser> getFacebookFriends(final String[] ids)
    {
        List<FacebookUser> facebookUsers = null;
        getFacebookFriendsThread thread = new getFacebookFriendsThread(ids);
        Thread t = new Thread(thread);
        t.start();
        try
        {
            t.join();
            facebookUsers = thread.getfacebookUsers();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in getFacebookFriends in StageHandler", e.toString());
        }
        return facebookUsers;
    }

    public class getFacebookGlobalThread implements Runnable
    {
        private List<FacebookUser> facebookUsers = null;
        public void run()
        {
            ObjectMapper objectMapper = new ObjectMapper();
            try
            {
                facebookUsers = objectMapper.readValue(new URL(address + "/getFacebookGlobal"), new TypeReference<List<FacebookUser>>(){});
            }
            catch (Exception e)
            {
                logger.error("Exception in connection with RestAPI in getFacebookGlobalThread in StageHandler", e.toString());
            }
        }
        public List<FacebookUser> getfacebookUsers()
        {
            return facebookUsers;
        }
    }

    public List<FacebookUser> getFacebookGlobal()
    {
        List<FacebookUser> facebookUsers = null;
        getFacebookGlobalThread thread = new getFacebookGlobalThread();
        Thread t = new Thread(thread);
        t.start();
        try
        {
            t.join();
            facebookUsers = thread.getfacebookUsers();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in getFacebookGlobal in StageHandler", e.toString());
        }
        return facebookUsers;
    }
    public String getFacebookID()
    {
        String id = Profile.getCurrentProfile().getId();
        System.out.println("Facebook Id: " + id);
        return id;

    }
    public String getFacebookName()
    {
        String name  = Profile.getCurrentProfile().getName();
        System.out.println("Facebook Name: " + name);
        return name;
    }
}