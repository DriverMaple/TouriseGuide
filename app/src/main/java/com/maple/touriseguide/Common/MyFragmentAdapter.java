package com.maple.touriseguide.Common;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by rrr on 2017/10/26.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    public FragmentManager fm;
    public List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.list = list;
    }

    /**
     * 返回需要展示的fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        fragment = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", "" + position);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 返回需要展示的fangment数量
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        Fragment fragment = list.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }
}
