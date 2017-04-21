package b219.p2prototype;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by trend on 21.4.2017 г..
 */

public class MoodFragment extends Fragment {
    // Store instance variables
    //private String title;
    //private int page;

    // newInstance constructor for creating fragment with arguments
    public static MoodFragment newInstance() {
        MoodFragment fragmentMood = new MoodFragment();
        //Bundle args = new Bundle();
        //args.putInt("someInt", page);
        //args.putString("someTitle", title);
        //fragmentMood.setArguments(args);
        return fragmentMood;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        //title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, new GenreFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        };
        View rootView = inflater.inflate(R.layout.fragment_mood, container, false);
        ImageView smileyBad = (ImageView) rootView.findViewById(R.id.smileyBad);
        ImageView smileyNeutral = (ImageView) rootView.findViewById(R.id.smileyNeutral);
        ImageView smileyGood = (ImageView) rootView.findViewById(R.id.smileyGood);
        smileyBad.setOnClickListener(myListener);
        smileyNeutral.setOnClickListener(myListener);
        smileyGood.setOnClickListener(myListener);
        return rootView;
    }
}
