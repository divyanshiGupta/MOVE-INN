package moveinn.par.thunderbolt.moveinn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CommercialSearch extends Fragment {
    View rootview;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.commercialsearch, container, false);
        ViewPager mViewPager = (ViewPager) rootview.findViewById(R.id.viewPager);
        // Set the ViewPagerAdapter into ViewPager
        mViewPager.setAdapter(new commertialadapter(getChildFragmentManager()));


        return rootview;
    }
}