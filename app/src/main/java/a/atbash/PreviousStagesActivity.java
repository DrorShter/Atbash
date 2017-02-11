package a.atbash;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PreviousStagesActivity extends AppCompatActivity
{
    private int page = 1;
    private int thisButtonNumber = 1;
    private StageHandler stageHandler = new StageHandler();
    private int lastLevel = stageHandler.getLastLevel(); //last level the user succeeded
    private int numberOfStagesInPage = 20;
    private int LevelCount = 44; //should be from db
    private Button backpage;
    private Button nextpage;
    public class ButtonAdapter  extends BaseAdapter {

        private Context mContext;

        private boolean check;
        public ButtonAdapter(Context c) {
            mContext = c;
        }

        public int getCount()
        {
            if (page == ((LevelCount-1) / 20) + 1) //if last page
            {
                return LevelCount%numberOfStagesInPage;
            }
            else
            {
                return numberOfStagesInPage;
            }
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ButtonViewItem button;
            nextpage = (Button) findViewById(R.id.nextPage);
            backpage = (Button) findViewById(R.id.backPage);
            if (convertView == null) {
                button = new ButtonViewItem(mContext);
                button.setLayoutParams(new GridView.LayoutParams(200, 100));
                button.setPadding(1, 1, 1, 1);
                button.setNumber(thisButtonNumber);
                thisButtonNumber++;
                button.setText(Integer.toString(button.getNumber()));
                if (lastLevel < button.getNumber())
                {
                    button.setEnabled(false);
                }
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent(PreviousStagesActivity.this, StageActivity.class);
                        System.out.println(Integer.toString(button.getNumber()));
                        i.putExtra("Stage", button.getNumber());
                        PreviousStagesActivity.this.startActivity(i);
                    }
                });
            } else {
                button = (ButtonViewItem) convertView;
            }
            nextpage.setVisibility(View.VISIBLE);
            backpage.setVisibility(View.VISIBLE);
            System.out.println("page = " + page);
            if (page == 1) //if first page
            {
               backpage.setVisibility(View.INVISIBLE);
            }
            if (page == ((LevelCount-1) / 20) + 1) //if last page
            {
                nextpage.setVisibility(View.INVISIBLE);
            }
            return button;
        }
    }
    public void goToNextPage(View view)
    {
        page++;
        thisButtonNumber = 1 + (page-1)*20;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }
    public void goToBackPage(View view)
    {
        page--;
        thisButtonNumber = 1 + (page-1)*20;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }
    public void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.previous_stages);
         GridView gridview = (GridView) findViewById(R.id.gridview);
         ButtonAdapter buttonAdapter = new ButtonAdapter(this);
         gridview.setAdapter(buttonAdapter);
    }
    public void GoToMainActivity(View view)
    {
        Intent intent = new Intent(PreviousStagesActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
