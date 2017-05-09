package b219.p2prototype;


import android.app.Fragment;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static b219.p2prototype.MainActivity.USER_INPUT;


public class MoodFragment extends Fragment {

    static final String MOOD_ID = "MOOD_ID";
    Fragment genreFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                if(((MainActivity) getActivity()).userIn!=null){
                    args.putParcelableArrayList(USER_INPUT, ((MainActivity) getActivity()).userIn);
                    genreFrag = new GenreFragment();
                }
                else{
                    genreFrag = new GenreFragment();
                }

                genreFrag.setArguments(args);
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "fragment_main.xml" as the reference to replace fragment
				 */

                trans.replace(R.id.root_frame, genreFrag, "genreFrag");

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
