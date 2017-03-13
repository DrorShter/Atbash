package a.atbash;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class LeaderboardsActivity extends AppCompatActivity
{
    private static int COUNT_OF_FRIENDS = 10;
    private String[][] namesAndScores = {{"a", "a"}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}};
    private final Logger logger = LoggerFactory.getLogger(LeaderboardsActivity.class);
    public class TextViewAdapter  extends BaseAdapter
    {
        private Context mContext;
        public TextViewAdapter(Context c) {
            mContext = c;
        }
        public int getCount()
        {
            return COUNT_OF_FRIENDS * 2;
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView textView = new TextView(LeaderboardsActivity.this);
            textView.setLayoutParams(new GridView.LayoutParams(200, 100));
            textView.setPadding(1, 1, 1, 1);
            if (position%2 == 0) //if name
            {
                textView.setText(namesAndScores[position/2][0]);
            }
            else //if score
            {
                textView.setText(namesAndScores[position/2][1]);
            }
            return textView;
        }
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboards);
        LeaderboardsActivity.TextViewAdapter editTextAdapter = new LeaderboardsActivity.TextViewAdapter(this);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                    new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/friends", null, HttpMethod.GET, new GraphRequest.Callback()
                    {
                        public void onCompleted(GraphResponse response)
                        {
                            try
                            {
                                JSONObject jo = response.getJSONObject();
                                JSONArray ja = null;
                                ja = jo.getJSONArray("data");
                                System.out.println(ja); // just for debug
                                String[] ids = new String[ja.length()];
                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject friend = ja.getJSONObject(i);
                                    //String name = friend.getString("name"); //working but we dont need it
                                    String id = friend.getString("id");
                                    ids[i] = id;
                                }
                                StageHandler stageHandler = new StageHandler();
                                namesAndScores = stageHandler.getNamesAndScores(ids);
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
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(editTextAdapter);
    }
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(LeaderboardsActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
