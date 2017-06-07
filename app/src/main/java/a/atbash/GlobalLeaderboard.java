package a.atbash;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class GlobalLeaderboard extends Fragment
{
    private static int COUNT_OF_PLAYERS = 10; //count of players
    private boolean alert = false; //member
    private List<FacebookUser> facebookUsers = null; //list
    private final Logger logger = LoggerFactory.getLogger(LeaderboardsActivity.class);
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final StageHandler stageHandler = new StageHandler(getContext());
        TextViewAdapter editTextAdapter = new TextViewAdapter(getActivity());
        Thread serverThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                facebookUsers = stageHandler.getFacebookGlobal();
            }
        });
        serverThread.start();
        View view = inflater.inflate(R.layout.global_leaderboard, container, false);//
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(editTextAdapter);
        return view;
    }
    private class TextViewAdapter  extends BaseAdapter
    {
        private Context mContext; //member
        private TextViewAdapter(Context c)
        {
            mContext = c;
        } //member
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
                if (facebookUsers.size() > position/2 && facebookUsers.get(position/2) != null)
                {
                    if (position%2 == 0) //if Name
                    {
                        textView.setText(facebookUsers.get(position/2).getName());
                    }
                    else //if CurrentStageNumber
                    {
                        String textOfCurrentStageNumber = "" + facebookUsers.get(position/2).getCurrentStageNumber();
                        textView.setText(textOfCurrentStageNumber);
                    }
                }
            }
            else
            {
                if (!alert) //pop-up
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage(getString(R.string.failedLoadingLeaderboards)).create().show();
                    alert = !alert;
                }
            }
            return textView;
        }
    }
}