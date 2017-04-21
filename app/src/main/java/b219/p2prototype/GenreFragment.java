package b219.p2prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by trend on 21.4.2017 г..
 */

public class GenreFragment extends Fragment {
    // Store instance variables
    //private String title;
    //private int page;

    // newInstance constructor for creating fragment with arguments
    public static GenreFragment newInstance() {
        GenreFragment fragmentGenre = new GenreFragment();
        //Bundle args = new Bundle();
        //args.putInt("someInt", page);
        //args.putString("someTitle", title);
        //fragmentMood.setArguments(args);
        return fragmentGenre;
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
        View rootView = inflater.inflate(R.layout.fragment_genre, container, false);
        TextView genreQ = (TextView) rootView.findViewById(R.id.genre_question);
        Spinner genres = (Spinner) rootView.findViewById(R.id.spinner_genres);
        ArrayAdapter<CharSequence> genresAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.genres, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genres.setAdapter(genresAdapter);
        return rootView;
    }
}
