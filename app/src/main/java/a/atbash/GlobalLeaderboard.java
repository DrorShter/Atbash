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

public class GlobalLeaderboard extends Fragment
{
    private static int COUNT_OF_PLAYERS = 10;
    private String[][] namesAndScores;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        StageHandler stageHandler = new StageHandler();
        namesAndScores = stageHandler.getNamesAndStagesGlobal();
        TextViewAdapter editTextAdapter = new TextViewAdapter(getActivity());
        View view = inflater.inflate(R.layout.friends_leaderboard, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview2);
        gridview.setAdapter(editTextAdapter);
        return view;
    }
}