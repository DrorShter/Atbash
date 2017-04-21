package a.atbash;

import android.content.Context;
import com.facebook.Profile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.id.input;

class StageHandler
{
    private final Logger logger = LoggerFactory.getLogger(StageHandler.class);
    private StageDAL stageDAL;
    private final Context context;
    private String address;
    StageHandler(Context context)
    {
        this.context = context;
        stageDAL = new StageDAL(context);
        final String IP = "192.168.9.27";
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
        return stageDAL.getCurrentStageNumber();
    }

    public void setCurrentStageNumber (int currentStage)
    {
        stageDAL.setCurrentStageNumber (currentStage);
    }
    public void updateStagesFromServerIfNeeded()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (getCount() < getCountFromServer() - 1)
                {
                    updateStagesFromServer();
                }
            }
        });
        t.start();
    }
    public void updateStagesFromServer()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
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
        }
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
        if (user.getFacebookID() != null && user.getName() != null)
        {
            user.setName(hebrewToEnglish(user.getName()));
            System.out.println("after hebrew to english");
            final String send = address + "/updateFacebookUser/" + user.getFacebookID() +  "/" + user.getName().replaceAll("\\s+","") + "/"  + String.valueOf(user.getCurrentStageNumber());
            System.out.println(send);
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run() {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Boolean b = false;
                    try
                    {
                        b = objectMapper.readValue(new URL(send), new TypeReference<Boolean>() {});
                        if (!b)
                        {
                            throw new Exception();
                        }
                    }
                    catch (Exception e)
                    {
                        logger.error("Exception in connection with RestAPI in updateFacebookUser in StageHandler" +e.toString(), e.toString());
                    }
                }
            });
            thread.start();
            try
            {
                thread.join();
            } catch (InterruptedException e)
            {
                logger.error("Exception in in joining the thread in updateFacebookUser in StageHandler", e.toString());
            }
        }
    }

    public void newFacebookUser()
    {
        updateFacebookUser(1);
    }

    public List<FacebookUser> getFacebookFriends(final String[] ids)
    {
        List<FacebookUser> facebookUsers = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            String idsString =  makeIdsOneString(ids);
            if (!idsString.equals(""))
            {
                String send = address + "/getFacebookFriends/" + idsString;
                System.out.println(send);
                facebookUsers = objectMapper.readValue(new URL(send), new TypeReference<List<FacebookUser>>(){});
                System.out.println(facebookUsers);
            }
            else
            {
                System.out.println("no friends found");
            }
        }
        catch (Exception e)
        {
            logger.error("Exception in connection with RestAPI in getFacebookFriends in StageHandler" + e.toString(), e.toString());
        }
        return facebookUsers;
    }

    public List<FacebookUser> getFacebookGlobal()
    {
        List<FacebookUser> facebookUsers = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            facebookUsers = objectMapper.readValue(new URL(address + "/getFacebookGlobal"), new TypeReference<List<FacebookUser>>(){});
        }
        catch (Exception e)
        {
            logger.error("Exception in connection with RestAPI in getFacebookGlobalThread in StageHandler" + e.toString(), e.toString());
        }
        return facebookUsers;
    }

    public String getFacebookID()
    {
        if (Profile.getCurrentProfile() != null)
        {
            String id = Profile.getCurrentProfile().getId();
            System.out.println("Facebook Id: " + id);
            return id;
        }
        return null;

    }
    public String getFacebookName()
    {
        if (Profile.getCurrentProfile() != null)
        {
            String name  = Profile.getCurrentProfile().getName();
            System.out.println("Facebook Name: " + name);
            return name;
        }
        return null;
    }
    public class GetCountFromServerThread implements Runnable
    {
        private int count;
        public void run()
        {
            ObjectMapper objectMapper = new ObjectMapper();
            try
            {
                count = objectMapper.readValue(new URL(address + "/getCount"), new TypeReference<Integer>(){});
                System.out.println(count);
            }
            catch (Exception e)
            {
                logger.error("Exception in connection with RestAPI in GetCountFromServerThread in StageHandler", e.toString());
            }
        }
        public int getCount()
        {
            return count;
        }

    }
    public int getCountFromServer()
    {
        int count = 0;
        GetCountFromServerThread thread = new GetCountFromServerThread();
        Thread t = new Thread(thread);
        t.start();
        try
        {
            t.join();
            count = thread.getCount();
        }
        catch (InterruptedException e)
        {
            logger.error("Exception in in joining the thread in getCountFromServer in StageHandler", e.toString());
        }
        return count;
    }
    private String makeIdsOneString(String[] ids)
    {
        String s = "";
        if (ids != null)
        {
            for (int i=0;i<ids.length-1;i++)
            {
                if (ids[i] != null)
                {
                    s += ids[i];
                    s += ",";
                }
            }
            if (ids[ids.length - 1] != null)
            {
                s+=ids[ids.length - 1];
            }
        }
        System.out.println(s);
        return s;
    }
    private String hebrewToEnglish(String hebrew)
    {
        String english = "";
        if (hebrew.matches (".*[א-ת]+.*"))
        {
            english += "@";
            for (int i=0;i<hebrew.length();i++)
            {
                switch(hebrew.charAt(i))
                {
                    case 'א':
                        english += 'a';
                        break;
                    case 'ב' :
                        english += "b";
                        break;
                    case 'ג':
                        english += 'c';
                        break;
                    case 'ד':
                        english += 'd';
                        break;
                    case 'ה':
                        english += 'e';
                        break;
                    case 'ו':
                        english += 'f';
                        break;
                    case 'ז':
                        english += 'g';
                        break;
                    case 'ח':
                        english += 'h';
                        break;
                    case 'ט':
                        english += 'i';
                        break;
                    case 'י':
                        english += 'j';
                        break;
                    case 'כ':
                        english += 'k';
                        break;
                    case 'ל':
                        english += 'l';
                        break;
                    case 'מ':
                        english += 'm';
                        break;
                    case 'נ':
                        english += 'n';
                        break;
                    case 'ס':
                        english += 'o';
                        break;
                    case 'ע':
                        english += 'p';
                        break;
                    case 'פ':
                        english += 'q';
                        break;
                    case 'צ':
                        english += 'r';
                        break;
                    case 'ק':
                        english += 's';
                        break;
                    case 'ר':
                        english += 't';
                        break;
                    case 'ש':
                        english += 'u';
                        break;
                    case 'ת':
                        english += 'v';
                        break;
                    case 'ך':
                        english += 'w';
                        break;
                    case 'ם':
                        english += 'x';
                        break;
                    case 'ן':
                        english += 'y';
                        break;
                    case 'ף':
                        english += 'z';
                        break;
                    case 'ץ':
                        english += '#';
                        break;
                    default:
                        english += hebrew.charAt(i);
                }
            }
        }
        System.out.println("english = " + english);
        return english;
    }
}