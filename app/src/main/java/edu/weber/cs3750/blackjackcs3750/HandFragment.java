package edu.weber.cs3750.blackjackcs3750;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.Hand;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;


public class HandFragment extends Fragment {

    private Hand mHand;

    private TextView txvCurrentHand;
    private TextView txvHandCount;

    private OnPlayerInteractionListener mListener;

    public HandFragment() {
        // Required empty public constructor
    }


    // add a card to the player's hand
    public void addCard(Card card) {
        if(mHand == null)
            mHand = new Hand();
        mHand.addCard(card);
        updateView();
        findHandStatus();
    }

    public int getHandCount() {
        return mHand.getCardCount();
    }


    // checks the status of the hand. if is a Natural or a Bust then tell the main activity
    private void findHandStatus() {
        if (mHand.getCardCount() > 21) {
            mListener.handEndStatus(HandStatus.BUST);
        }
        else if (mHand.getCardCount() == 21) {
            mListener.handEndStatus(HandStatus.NATURAL);
        }
    }

    private void updateView() {
        //return if the textview is null
        // if the textview is null the view probably hasn't been created yet.
        // this function will run when the view is created
        if(txvHandCount == null)
            return;

        txvCurrentHand.setText(mHand.toString());
        String handCountText;

        if(mHand.getCardCount() < 21)
            handCountText = String.valueOf(mHand.getCardCount());
        else if (mHand.getCardCount() == 21)
            handCountText = "BlackJack";
        else
            handCountText = "Bust";

        txvHandCount.setText(handCountText);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hand, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(mHand == null)
            mHand = new Hand();

        txvCurrentHand = (TextView) view.findViewById(R.id.currentHand);
        txvHandCount = (TextView) view.findViewById(R.id.handCount);

        updateView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlayerInteractionListener) {
            mListener = (OnPlayerInteractionListener) context;
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

    public interface OnPlayerInteractionListener {
        void handEndStatus(HandStatus status);
    }
}
