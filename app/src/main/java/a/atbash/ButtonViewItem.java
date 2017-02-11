package a.atbash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


public class ButtonViewItem extends Button {
        private int number = 0;

        public ButtonViewItem(Context context) {
            super(context);
        }

        public ButtonViewItem(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ButtonViewItem(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
        public void setNumber(int number)
        {
            this.number = number;
        }
        public int getNumber()
        {
          return number;
        }
        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
        }
    }