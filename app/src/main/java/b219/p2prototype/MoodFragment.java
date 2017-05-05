package b219.p2prototype;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by trend on 21.4.2017 г..
 */

public class MoodFragment extends Fragment {
    //OnMoodSelectedListener myCallBack;
    //private final String TAG = "MoodFragment";
    private final String MOOD_ID = "MOOD_ID";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("MoodFragment: onAttach() called.");
    }

    /*public interface OnMoodSelectedListener{
        void onMoodSelected(int moodId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        System.out.println("OnAttach() called.");

        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                myCallBack = (OnMoodSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnMoodSelectedListener");
            }
        }
    }*/



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
        System.out.println("MoodFragment: OnCreate() called.");

    }



    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(v.getId());
                //myCallBack.onMoodSelected(v.getId());
                Bundle args = new Bundle();
                if(v.getId()==R.id.smileyBad){
                    args.putInt(MOOD_ID, 0);
                }
                else if(v.getId()==R.id.smileyNeutral){
                    args.putInt(MOOD_ID, 1);
                }
                else if(v.getId()==R.id.smileyGood){
                    args.putInt(MOOD_ID, 2);
                }
                /*switch(v.getId()){
                    case R.id.smileyBad:
                        args.putInt(MOOD_ID, 0);
                        break;
                    case R.id.smileyNeutral:
                        args.putInt(MOOD_ID, 1);
                        break;
                    case R.id.smileyGood:
                        args.putInt(MOOD_ID, 2);
                        break;
                }*/
                //args.putInt(MOOD_ID, v.getId());
                Fragment genreFrag = new GenreFragment();
                genreFrag.setArguments(args);
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "fragment_main.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, genreFrag);

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
        System.out.println("MoodFragment: onCreateView() called.");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("MoodFragment: onActivityCreated() called.");
    }

}
