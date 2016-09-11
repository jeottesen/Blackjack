package edu.weber.cs3750.blackjackcs3750.DialogFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import edu.weber.cs3750.blackjackcs3750.R;


public class GameRulesFragment extends Fragment {

    Button btnDismiss;
    ScrollView scroller;

    public GameRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView =  inflater.inflate(R.layout.fragment_game_rules, container, false);
        theView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        scroller = (ScrollView)theView.findViewById(R.id.scroller);

        btnDismiss = (Button)theView.findViewById(R.id.btn_dismiss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return theView;
    }

}
