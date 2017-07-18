package moveinn.par.thunderbolt.moveinn;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class hometabadapter extends FragmentStatePagerAdapter {

    private String[] titles = {"Buy", "Rent" };

    public hometabadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new buy();
            case 1:
                return new rent();

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