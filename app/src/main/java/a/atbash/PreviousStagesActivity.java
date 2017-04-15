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
    private int page = 1;
    private StageHandler stageHandler;
    private int CurrentStageNumber;
    private static final int NUMBER_OF_STAGES_IN_PAGE = 20;
    private GestureDetectorCompat detector;
    private int count;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        stageHandler= new StageHandler(this);
        CurrentStageNumber=stageHandler.getCurrentStageNumber();
        count=stageHandler.getCount();
        setContentView(R.layout.previous_stages);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    private class ButtonAdapter extends BaseAdapter
    {
        private Context mContext;
        private ButtonAdapter(Context c) {
            mContext = c;
        }
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
            final ButtonViewItem button;
            Button nextpage = (Button) findViewById(R.id.nextPage);
            Button backpage = (Button) findViewById(R.id.backPage);
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
            nextpage.setVisibility(View.VISIBLE);
            backpage.setVisibility(View.VISIBLE);
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

    public void goToNextPage(View view)
    {
        page++;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    public void goToBackPage(View view)
    {
        page--;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ButtonAdapter buttonAdapter = new ButtonAdapter(this);
        gridview.setAdapter(buttonAdapter);
    }

    private int firstButtonOfPage(int page)
    {
        return 1 + (page - 1) * NUMBER_OF_STAGES_IN_PAGE;
    }

    private int getLastPage(int count)
    {
        return ((count - 1) / NUMBER_OF_STAGES_IN_PAGE) + 1;
    }

    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(PreviousStagesActivity.this, MainActivity.class);
        startActivity(intent);
    }


}