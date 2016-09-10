package edu.weber.cs3750.blackjackcs3750;

import android.os.Bundle;
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
    private boolean isPlayer;
    private TextView txvCurrentHand;
    private TextView txvHandCount;


    public HandFragment() {
        // Required empty public constructor
    }

    public static HandFragment newInstance(boolean isPlayer) {
        HandFragment handFragment = new HandFragment();

        Bundle args = new Bundle();
        args.putBoolean("is_player", isPlayer);

        handFragment.setArguments(args);

        return handFragment;
    }

    // add a card to the player's hand
    public void addCard(Card card) {
        if (mHand == null)
            mHand = new Hand();
        mHand.addCard(card);
        updateView();
    }

    public void removeAllCards() {
        if (mHand == null)
            mHand = new Hand();
        mHand.removeAllCards();
        updateView();
    }

    public int getHandCount() {
        return mHand.getCardCount();
    }


    // checks the status of the hand. if is a Natural or a Bust then tell the main activity
    public HandStatus getHandStatus() {
        return mHand.getHandStatus();
    }

    private void updateView() {
        //return if the textview is null
        // if the textview is null the view probably hasn't been created yet.
        // this function will run when the view is created
        if (txvHandCount == null)
            return;

        txvCurrentHand.setText(mHand.toString());
        String handCountText = "";
        int cardCount = mHand.getCardCount();

        switch (mHand.getHandStatus()) {
            case NATURAL:
            case BLACKJACK:
                handCountText += "BlackJack ";
                break;
            case BUST:
                handCountText = "Bust ";
                break;
        }
        handCountText += cardCount;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mHand == null)
            mHand = new Hand();

        if (getArguments() != null) {
            isPlayer = getArguments().getBoolean("is_player");
        }

        txvCurrentHand = (TextView) view.findViewById(R.id.currentHand);
        txvHandCount = (TextView) view.findViewById(R.id.handCount);
        updateView();
    }
}
