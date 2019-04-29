package rs.edu.raf.movies.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import rs.edu.raf.movies.R;

import rs.edu.raf.movies.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

public class SimplePagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 1;
    public static final int FRAGMENT_ADD_MOVIE = 0;
    public static final int FRAGMENT_MOVIES = 1;

    private List<String> mTitles;

    public SimplePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        initTitles(context);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case FRAGMENT_ADD_MOVIE:
                return SecondFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    private void initTitles(Context context) {
        mTitles = new ArrayList<>();
        mTitles.add(context.getString(R.string.fragment_title_1));
    }
}
