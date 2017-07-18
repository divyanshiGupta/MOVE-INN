package moveinn.par.thunderbolt.moveinn;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class commertialadapter extends FragmentStatePagerAdapter {

    private String[] titles = {"Buy", "Rent" };

    public commertialadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new buy_comm();
            case 1:
                return new rent_comm();

        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
