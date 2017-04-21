package b219.p2prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by trend on 21.4.2017 г..
 */

public class RootFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

    /*
     * When this container fragment is created, we fill it with our first
     * "real" fragment
     */
        transaction.replace(R.id.root_frame, new MoodFragment());

        transaction.commit();

        return view;
    }
}
