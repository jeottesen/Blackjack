package edu.weber.cs3750.blackjackcs3750;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gisela on 9/7/2016.
 */
public class DealerHandFragment extends HandFragment {


    public DealerHandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setFirstCardFaceUp(false);
        return inflater.inflate(R.layout.fragment_hand, container, false);
    }



}
