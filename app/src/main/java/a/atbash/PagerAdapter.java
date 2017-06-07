package a.atbash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//PagerAdapter class made for the tabbed display of leaderboards
class PagerAdapter extends FragmentStatePagerAdapter
{
    private int mNumOfTabs = 2; //member
    PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm); //super
        this.mNumOfTabs = NumOfTabs;
    }

    //This function gets int and returns Fragment
    //This function return specific tab
    @Override
    public Fragment getItem(int position)
    {
        position = 1-position;
        switch (position)
        {
            case 0: //if friends
                return new FriendsLeaderboard();
            case 1: //if global
                return new GlobalLeaderboard();
            default:
                return null;
        }
    }

    //This function gets void and returns int
    //This function return number of tabs
    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}