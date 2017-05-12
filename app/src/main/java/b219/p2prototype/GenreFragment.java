package b219.p2prototype;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static b219.p2prototype.MainActivity.USER_INPUT;
import static b219.p2prototype.MoodFragment.MOOD_ID;


public class GenreFragment extends Fragment {

    OnEntryRegisteredListener myCallBack;

    public interface OnEntryRegisteredListener{
        void onEntryRegistered(ArrayList<UserInput> userInput);
    }

    private int moodClicked;
    ArrayList<UserInput> userIn = new ArrayList<UserInput>();
    Spinner genres;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moodClicked = getArguments().getInt(MOOD_ID);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getArguments().getParcelableArrayList(USER_INPUT)!=null) {
            userIn = getArguments().getParcelableArrayList(USER_INPUT);

        }

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(genres.getSelectedItemPosition()==0){
                    Toast toast = Toast.makeText(getContext(), "Please select a genre!", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    userIn.add(new UserInput(moodClicked, genres.getSelectedItemPosition()));

                    myCallBack.onEntryRegistered(userIn);
                    //getFragmentManager().popBackStack();
                    new AlertDialog.Builder(getContext())
                            .setTitle("Your entry has been registered!")
                            //.setMessage("Your entry has been registered!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getFragmentManager().popBackStack();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }

            }
        };

        View rootView = inflater.inflate(R.layout.fragment_genre, container, false);
        Button acceptButton = (Button) rootView.findViewById(R.id.button);

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
