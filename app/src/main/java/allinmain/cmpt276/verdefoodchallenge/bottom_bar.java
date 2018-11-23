package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bottom_bar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bottom_bar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottom_bar extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public bottom_bar() {
        // Required empty public constructor
    }

    public static bottom_bar newInstance() {
        return new bottom_bar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_bar,container,false);
        ImageView foods = v.findViewById(R.id.toGreenfood);
        ImageView calculate = v.findViewById(R.id.toResult);
        ImageView community = v.findViewById(R.id.toCommunity);
        ImageView profile = v.findViewById(R.id.toProfile);
        ImageView meals = v.findViewById(R.id.toMeals);

        foods.setOnClickListener(this);
        calculate.setOnClickListener(this);
        community.setOnClickListener(this);
        profile.setOnClickListener(this);
        meals.setOnClickListener(this);

        return v;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        Intent move=null;

        Activity current_activity = getActivity();

        switch(view.getId()) {

            case R.id.toCommunity:
                move = new Intent(current_activity,community.class);
                break;
            case R.id.toGreenfood:
                move = new Intent(current_activity,GreenFoodActivity.class);
                break;
            case R.id.toResult:
                move = new Intent(current_activity,Co2CalActivity.class);
                break;
            case R.id.toProfile:
                move = new Intent(current_activity,Profile.class);
                break;
            case R.id.toMeals:
                move = new Intent(current_activity,Meal_Search.class);
                break;

        }

        current_activity.startActivity(move);
//        current_activity.finish();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
