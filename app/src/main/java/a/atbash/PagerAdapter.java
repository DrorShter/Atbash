package a.atbash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class PagerAdapter extends FragmentStatePagerAdapter
{
    private int mNumOfTabs = 2;
    PagerAdapter(FragmentManager fm, int NumOfTabs)
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
                return new FriendsLeaderboard();
            case 1:
                return new GlobalLeaderboard();
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