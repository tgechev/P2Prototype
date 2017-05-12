package b219.p2prototype;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static b219.p2prototype.MainActivity.USER_INPUT;




public class DataVisFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        if(getArguments().getParcelableArrayList(USER_INPUT)!=null){
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
