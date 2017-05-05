package b219.p2prototype;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by trend on 21.4.2017 г..
 */

public class GenreFragment extends Fragment {

    OnEntryRegisteredListener myCallBack;

    public interface OnEntryRegisteredListener{
        void onEntryRegistered(ArrayList<UserInput> userInput);
    }
    // Store instance variables
    //private String title;
    //private int page;

    private final String MOOD_ID = "MOOD_ID";
    private int moodClicked;
    ArrayList<UserInput> userIn = new ArrayList<UserInput>();
    Spinner genres;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        System.out.println("GenreFragment: OnAttach() called.");

        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                myCallBack = (OnEntryRegisteredListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnMoodSelectedListener");
            }
        }
    }

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
        moodClicked = getArguments().getInt(MOOD_ID);
        //userIn.add(new UserInput(3, "Rock"));
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent tut = new Intent(getContext(), TutorialActivity.class);
                //startActivity(tut);
                userIn.add(new UserInput(moodClicked, genres.getSelectedItemPosition()));
                //System.out.println(genres.getSelectedItemPosition());
                myCallBack.onEntryRegistered(userIn);



            }
        };
        View rootView = inflater.inflate(R.layout.fragment_genre, container, false);
        Button acceptButton = (Button) rootView.findViewById(R.id.button);
        //TextView genreQ = (TextView) rootView.findViewById(R.id.genre_question);
        genres = (Spinner) rootView.findViewById(R.id.spinner_genres);
        ArrayAdapter<CharSequence> genresAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.genres, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genres.setAdapter(genresAdapter);
        acceptButton.setOnClickListener(myListener);
        return rootView;
    }
}
