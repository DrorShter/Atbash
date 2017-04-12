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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GlobalLeaderboard extends Fragment
{
    private static int COUNT_OF_PLAYERS = 10;
    private List<FacebookUser> facebookUsers;
    private final Logger logger = LoggerFactory.getLogger(LeaderboardsActivity.class);
    public class TextViewAdapter  extends BaseAdapter
    {
        private Context mContext;
        public TextViewAdapter(Context c)
        {
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
                    if (position%2 == 0) //if Name
                    {
                        textView.setText(facebookUsers.get(position/2).getName());
                    }
                    else //if CurrentStageNumber
                    {
                        textView.setText("" + facebookUsers.get(position/2).getCurrentStageNumber());
                    }
                }
            }
            else
            {
                System.out.println("facebookUsers is null");
            }
            return textView;
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        StageHandler stageHandler = new StageHandler(getContext());
        facebookUsers = stageHandler.getFacebookGlobal();
        TextViewAdapter editTextAdapter = new TextViewAdapter(getActivity());
        View view = inflater.inflate(R.layout.friends_leaderboard, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview2);
        gridview.setAdapter(editTextAdapter);
        return view;
    }
}