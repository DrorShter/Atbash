package a.atbash;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs = 2;
    public PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position)
    {
        position = 1-position;
        switch (position)
        {
            case 0:
                FriendsLeaderboard tab1 = new FriendsLeaderboard();
                return tab1;
            case 1:

                GlobalLeaderboard tab2 = new GlobalLeaderboard();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}