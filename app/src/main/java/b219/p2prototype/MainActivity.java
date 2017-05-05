package b219.p2prototype;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GenreFragment.OnEntryRegisteredListener/*implements MoodFragment.OnMoodSelectedListener*/{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link //FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    static final String USER_INPUT = "USER_INPUT";
    //DataVisFragment dataVisFrag = new DataVisFragment();
    RootFragment rootFrag = new RootFragment();
    ArrayList<UserInput> userIn = new ArrayList<UserInput>();
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    public void onEntryRegistered(ArrayList<UserInput> userInput){
        userIn = userInput;

        Toast t = Toast.makeText(this, "Genre chosen for last input: "+userIn.get(userIn.size()-1).getGenre(), Toast.LENGTH_SHORT);
        t.show();
    }

    /*public void onMoodSelected(int moodId){
        //DataVisFragment dVisFrag = (DataVisFragment) getFragmentManager().findFragmentById(R.id.vis_fragment);
        //DataVisFragment newFragment = new DataVisFragment();
        //Bundle args = new Bundle();
        //args.putInt(DataVisFragment.RED_VAL, 255);
        //args.putInt(DataVisFragment.GREEN_VAL, 0);
        //args.putInt(DataVisFragment.BLUE_VAL, 0);
        //newFragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        PApplet dataVis = new DataVis();
        PFragment fragment = new PFragment();
        fragment.setSketch(dataVis);
        fragmentManager.beginTransaction()
                .replace(R.id.vis_fragment, fragment)
                .commit();
        //Toast toast = Toast.makeText(getApplicationContext(), "onMoodSelected() called", Toast.LENGTH_SHORT);
        //toast.show();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Disable "Drag" for AppBarLayout (i.e. User can't scroll appBarLayout by directly touching appBarLayout - User can only scroll appBarLayout by only using scrollContent)
        if (appBarLayout.getLayoutParams() != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
            appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return false;
                }
            });
            layoutParams.setBehavior(appBarLayoutBehaviour);
        }


        /*Bundle args = new Bundle();
        args.putParcelableArrayList(USER_INPUT, userIn);
        dataVisFrag.setArguments(args);*/


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_tutorial){
            Intent tutIntent = new Intent(this, TutorialActivity.class);
            startActivity(tutIntent);
        }
        else{

            mViewPager.getAdapter().notifyDataSetChanged();
            /*FragmentManager fragmentManager = getFragmentManager();
            //Bundle args = new Bundle();
            //args.putInt(BACKGROUND_COLOR, 255);
            Fragment fragment = new DataVis();
            //fragment.setArguments(args);
            fragmentManager.beginTransaction()
                    .replace(R.id.vis_fragment, fragment)
                    .commit();*/
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance() {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                Toast t = Toast.makeText(getContext(), "on create view on placeholder fragment called", Toast.LENGTH_SHORT);
                t.show();

            return rootView;
        }
    }

    /**
     * A {@link //FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends SmartFragmentStatePagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return rootFrag;
            else {
                DataVisFragment dataVisFrag = new DataVisFragment();
                Bundle args = new Bundle();
                args.putParcelableArrayList(USER_INPUT, userIn);
                dataVisFrag.setArguments(args);

                return dataVisFrag;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Input Data";
                case 1:
                    return "Your Data";
            }
            return null;
        }


    }
}
