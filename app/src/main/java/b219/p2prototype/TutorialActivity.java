package b219.p2prototype;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;



public class TutorialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

    }
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
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            TextView tutorialPageTitle;
            TextView tutorialPageDesc;
            ImageView tutorialPageImage;
                if(getArguments().getInt(ARG_SECTION_NUMBER)==0) {
                    rootView = inflater.inflate(R.layout.fragment_tutorial_intro, container, false);
                }
                else if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                    rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
                    tutorialPageTitle = (TextView) rootView.findViewById(R.id.tutorial_page_title);
                    tutorialPageDesc = (TextView) rootView.findViewById(R.id.tutorial_page_description);
                    tutorialPageImage = (ImageView) rootView.findViewById(R.id.tutorial_page_image);
                    tutorialPageTitle.setText(R.string.tutorial_mood_title);
                    tutorialPageDesc.setText(R.string.tutorial_mood_desc);
                    tutorialPageImage.setImageResource(R.drawable.tutorial_mood);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                    rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
                    tutorialPageTitle = (TextView) rootView.findViewById(R.id.tutorial_page_title);
                    tutorialPageDesc = (TextView) rootView.findViewById(R.id.tutorial_page_description);
                    tutorialPageImage = (ImageView) rootView.findViewById(R.id.tutorial_page_image);
                    tutorialPageTitle.setText(R.string.tutorial_genre_title);
                    tutorialPageDesc.setText(R.string.tutorial_genre_desc);
                    tutorialPageImage.setImageResource(R.drawable.tutorial_genre);
                }
                else{
                    rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
                    tutorialPageTitle = (TextView) rootView.findViewById(R.id.tutorial_page_title);
                    tutorialPageDesc = (TextView) rootView.findViewById(R.id.tutorial_page_description);
                    tutorialPageImage = (ImageView) rootView.findViewById(R.id.tutorial_page_image);
                    tutorialPageTitle.setText(R.string.tutorial_dataVis_title);
                    tutorialPageDesc.setText(R.string.tutorial_dataVis_desc);
                    tutorialPageImage.setImageResource(R.drawable.tutorial_data_vis);
                }
            return rootView;
        }
    }
    private class SectionsPagerAdapter extends SmartFragmentStatePagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

    public void onClick(View view){
        finish();
    }
}
