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
    private int currButtonNumber = 1;
    private StageHandler stageHandler = new StageHandler(getApplicationContext());
    private int lastLevel = stageHandler.getLastLevel(); //last level the user succeeded
    private final int NUMBER_OF_STAGES_IN_PAGE = 18;
    private int levelCount = 44; //TODO: should be from db
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
            if (page == getLastPage(levelCount)) //if last page
            {
                return levelCount%NUMBER_OF_STAGES_IN_PAGE;
            }
            else
            {
                return NUMBER_OF_STAGES_IN_PAGE;
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
                button.setNumber(currButtonNumber);
                currButtonNumber++;
                button.setText(Integer.toString(button.getNumber()));
                button.setEnabled(lastLevel >= button.getNumber());
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
            if (page == 1) //if first page
            {
               backpage.setVisibility(View.INVISIBLE);
            }
            if (page == getLastPage(levelCount)) //if last page
            {
                nextpage.setVisibility(View.INVISIBLE);
            }
            return button;
        }
    }
    public void goToNextPage(View view)
    {
        page++;
        currButtonNumber = firstButtonOfPage(page);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }
    public void goToBackPage(View view)
    {
        page--;
        currButtonNumber = firstButtonOfPage(page);
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
    private int firstButtonOfPage (int page)
    {
        return  1 + (page-1)*NUMBER_OF_STAGES_IN_PAGE;
    }
    private int getLastPage (int levelCount)
    {
        return ((levelCount-1) / NUMBER_OF_STAGES_IN_PAGE) + 1;
    }
    public void GoToMainActivity(View view)
    {
        Intent intent = new Intent(PreviousStagesActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
