package a.atbash;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FriendsLeaderboard extends Fragment
{
    private static int COUNT_OF_PLAYERS = 10;
    private List<FacebookUser> facebookUsers;
    private final Logger logger = LoggerFactory.getLogger(LeaderboardsActivity.class);
    public class TextViewAdapter  extends BaseAdapter
    {
        private Context mContext;
        public TextViewAdapter(Context c) {
            mContext = c;
        }
        public int getCount()
        {
            return COUNT_OF_PLAYERS * 2;
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new GridView.LayoutParams(200, 100));
            textView.setPadding(1, 1, 1, 1);
            if (facebookUsers != null)
            {
                if (facebookUsers.get(position/2) != null)
                {
                    if (position % 2 == 0) //if Name
                    {
                        textView.setText(facebookUsers.get(position / 2).getName());
                    }
                    else //if CurrentStageNumber
                    {
                        textView.setText("" + facebookUsers.get(position / 2).getCurrentStageNumber());
                    }
                }
            }
            else
            {
                System.out.println("facebookUsers is null in friends");
            }
            return textView;


        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextViewAdapter editTextAdapter = new TextViewAdapter(getActivity());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/friends", null, HttpMethod.GET, new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response)
                    {
                        try
                        {
                            JSONObject jo = response.getJSONObject();
                            JSONArray ja = null;
                            ja = jo.getJSONArray("data");
                            System.out.println(ja); // just for debug
                            String[] ids = new String[ja.length()];
                            for (int i = 0; i < ja.length(); i++)
                            {
                                JSONObject friend = ja.getJSONObject(i);
                                //String name = friend.getString("name"); //working but we dont need it
                                String id = friend.getString("id");
                                ids[i] = id;
                            }
                            StageHandler stageHandler = new StageHandler(getContext());
                            facebookUsers = stageHandler.getFacebookFriends(ids);
                        }
                        catch (JSONException e)
                        {
                            logger.error("JsonException while parsing friends in LeaderboardsActivity", e.toString());
                        }
                    }
                }).executeAndWait();
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            logger.error("InterruptedException while joining the thread in LeaderboardsActivity", e.toString());
            System.out.println("");
        }
        View view = inflater.inflate(R.layout.global_leaderboard, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(editTextAdapter);
        return view;
    }
}