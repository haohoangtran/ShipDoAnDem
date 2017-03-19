package shipdoandem.amytateam.org.shipdoandem.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import shipdoandem.amytateam.org.shipdoandem.activities.MainActivity;
import shipdoandem.amytateam.org.shipdoandem.fragment.FavoriteFragment;
import shipdoandem.amytateam.org.shipdoandem.fragment.HighlightsFragment;
import shipdoandem.amytateam.org.shipdoandem.fragment.MostViewFragment;



public class Pager extends FragmentStatePagerAdapter {
    int tabCout;

    public Pager(FragmentManager fm, int tabCout) {
        super(fm);
        this.tabCout = tabCout;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:

                return new MostViewFragment();
            case 1:
                return new HighlightsFragment();
            case 2:
                return new FavoriteFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCout;
    }
}
