package b219.p2prototype;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GenreFragment.OnEntryRegisteredListener{



    static final String USER_INPUT = "USER_INPUT";
    RootFragment rootFrag = new RootFragment();


    ArrayList<UserInput> userIn = new ArrayList<UserInput>();


    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;



    public void onEntryRegistered(ArrayList<UserInput> userInput){
        userIn = userInput;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //RESTORE SAVED USER INPUT
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(USER_INPUT, null); //Retrieve previously saved data
        if (json != null) {
            Type type = new TypeToken<ArrayList<UserInput>>() {}.getType();
            userIn = gson.fromJson(json, type); //Restore previous data
        }





        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);


        // Create the adapter that will return a fragment for each tab of the activity.
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

        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_tutorial){
            Intent tutIntent = new Intent(this, TutorialActivity.class);
            startActivity(tutIntent);
        }
        else if(id == R.id.action_draw_vis_test){
            mViewPager.getAdapter().notifyDataSetChanged();
        }
        else{
            userIn = new ArrayList<UserInput>();
        }

        return super.onOptionsItemSelected(item);
    }


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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(userIn); //Convert the array to json

        editor.putString(USER_INPUT, json); //Put the variable in memory
        editor.apply();
    }
}
