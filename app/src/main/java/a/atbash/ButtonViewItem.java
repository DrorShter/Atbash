package a.atbash;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class ButtonViewItem extends AppCompatButton
{
        private int number = 0;
        //Constructor
        public ButtonViewItem(Context context)
        {
            super(context);
        }
        //Constructor
        public ButtonViewItem(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        //Constructor
        public ButtonViewItem(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
        }
        //This function gets int and returns void
        //It set number
        public void setNumber(int number)
        {
            this.number = number;
        }
        //This function gets void and returns int
        //It get number
        public int getNumber()
        {
          return number;
        }
        //This function gets int, int and returns void
        //It define the length and width of the view
        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
        }
}