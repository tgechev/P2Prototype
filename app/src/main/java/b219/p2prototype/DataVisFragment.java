package b219.p2prototype;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static b219.p2prototype.MainActivity.USER_INPUT;


/**
 * Created by trend on 24.4.2017 г..
 */

public class DataVisFragment extends Fragment {


    //private static final String BACKGROUND_COLOR = "bck_color";
    //private static final String USER_INPUT = "USER_INPUT";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        //title = getArguments().getString("someTitle");

        /*FragmentManager fragmentManager = getFragmentManager();
        PApplet sketch = new DataVis();
        PFragment fragment = new PFragment();
        fragment.setSketch(sketch);
        fragmentManager.beginTransaction()
                .replace(R.id.vis_fragment, fragment)
                .commit();*/


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        if(getArguments().getParcelableArrayList(USER_INPUT).isEmpty()){
            rootView = inflater.inflate(R.layout.fragment_no_data, container, false);
        }
        else {
            rootView = inflater.inflate(R.layout.fragment_vis, container, false);
            FragmentManager fragmentManager = getFragmentManager();
            Bundle args = new Bundle();

            args.putParcelableArrayList(USER_INPUT, getArguments().getParcelableArrayList(USER_INPUT));
            Fragment fragment = new DataVis();
            fragment.setArguments(args);
            fragmentManager.beginTransaction()
                    .replace(R.id.vis_fragment, fragment)
                    .commit();
        }

        return rootView;
    }
}
