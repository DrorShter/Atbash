package a.atbash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PreviousStagesActivity extends AppCompatActivity
{
    private int page = 1; //member
    private StageHandler stageHandler; //member
    private int CurrentStageNumber; //member
    private static final int NUMBER_OF_STAGES_IN_PAGE = 20; //member
    private int count; //member
    //This function gets Bundle and returns void
    //This function is the OnCreate
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); //super
        stageHandler= new StageHandler(this);
        CurrentStageNumber=stageHandler.getCurrentStageNumber();
        count=stageHandler.getCount();
        setContentView(R.layout.previous_stages);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    //This class is responsible for the displaying the button for the stages in this activity
    private class ButtonAdapter extends BaseAdapter
    {
        private Context mContext; //member
        private ButtonAdapter(Context c) {
            mContext = c;
        } //member
        public int getCount()
        {
            if (page == getLastPage(count)) //if last page
            {
                return count % NUMBER_OF_STAGES_IN_PAGE;
            }
            else
            {
                return NUMBER_OF_STAGES_IN_PAGE;
            }
        }

        public Object getItem(int position)
        {
            return null;
        }

        public long getItemId(int position)
        {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent)
        {
            final ButtonViewItem button; //final ButtonViewItem
            Button nextpage = (Button) findViewById(R.id.nextPage); //declare
            Button backpage = (Button) findViewById(R.id.backPage); //declare
            button = new ButtonViewItem(mContext);
            button.setLayoutParams(new GridView.LayoutParams(200, 100));
            button.setPadding(1, 1, 1, 1);
            button.setNumber(firstButtonOfPage(page) + position);
            button.setText(String.valueOf(button.getNumber()));
            button.setEnabled(CurrentStageNumber >= button.getNumber());
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(PreviousStagesActivity.this, StageActivity.class);
                    System.out.println(Integer.toString(button.getNumber()));
                    i.putExtra("Stage", button.getNumber());
                    PreviousStagesActivity.this.startActivity(i);
                }
            });
            nextpage.setVisibility(View.VISIBLE); //make nextpage visible (default)
            backpage.setVisibility(View.VISIBLE); //make backpage visible (default)
            if (page == 1) //if first page
            {
                backpage.setVisibility(View.INVISIBLE);
            }
            if (page == getLastPage(count)) //if last page
            {
                nextpage.setVisibility(View.INVISIBLE);
            }
            return button;
        }
    }

    //This function gets View and returns void
    //This function "moves" to next page
    public void goToNextPage(View view)
    {
        page++;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    //This function gets View and returns void
    //This function "moves" to previous page
    public void goToBackPage(View view)
    {
        page--;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    //This function gets int and returns int
    //This function returnes the first stage number in page
    private int firstButtonOfPage(int page)
    {
        return 1 + (page - 1) * NUMBER_OF_STAGES_IN_PAGE;
    }

    //This function gets int and returns int
    //This function returnes the last stage number in page
    private int getLastPage(int count)
    {
        return ((count - 1) / NUMBER_OF_STAGES_IN_PAGE) + 1;
    }

    //This function gets View and returns void
    //This function moves player to MainActivity
    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(PreviousStagesActivity.this, MainActivity.class);
        startActivity(intent);
    }


}