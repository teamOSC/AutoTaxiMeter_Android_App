package com.teamosc.autotaximeter.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.teamosc.autotaximeter.R;
import com.teamosc.autotaximeter.utils.FareData;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConvertFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConvertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConvertFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private static FareData mFareData;
    private static Context context;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConvertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConvertFragment newInstance(FareData fareData, Context c) {
        ConvertFragment fragment = new ConvertFragment();
        mFareData = fareData;
        context = c;
        return fragment;
    }

    public ConvertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convert, container, false);
        if (rootView != null) {
            Spinner selectorConvert = (Spinner) rootView.findViewById(R.id.selector_convert);
            String operators[] = mFareData.getOperators();
            ArrayAdapter<String> opAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, operators);
            opAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selectorConvert.setAdapter(opAdapter);


            //selectorConvert.setAdapter();
        }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
