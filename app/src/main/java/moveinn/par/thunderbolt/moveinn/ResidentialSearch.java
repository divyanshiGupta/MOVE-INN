package moveinn.par.thunderbolt.moveinn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ResidentialSearch extends Fragment {


    View rootview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.residentialsearch,container,false);
        ViewPager mViewPager = (ViewPager) rootview.findViewById(R.id.viewPager);
        // Set the ViewPagerAdapter into ViewPager
        mViewPager.setAdapter(new hometabadapter(getChildFragmentManager()));

        return rootview;

    }


}


