package edu.weber.cs3750.blackjackcs3750;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.Hand;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;


public class HandFragment extends Fragment {

    private View mView;
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

    public Card getCard(int index){
        return mHand.getCard(index);
    }

    protected void updateView() {
        if (mView == null)
            return;

        ArrayList<String> cardStrings = mHand.toStringArrayList();

        drawCardImages(cardStrings, this.getClass());
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

    private void drawCardImages(ArrayList<String> cardStrings, Class thisClass) {

        int beginningStartMargin = 60;
        int beginningElevation = 4;
        MainActivity mainActivity = (MainActivity) getActivity();
        RelativeLayout relativeLayoutHand = (RelativeLayout) mView.findViewById(R.id.rel_layout_hand);
        relativeLayoutHand.removeAllViews();  //remove what's there before adding more

        for (String card : cardStrings) {

            //each card has to have a new instance of LayoutParams
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) mainActivity.getResources().getDimension(R.dimen.card_width),
                            (int) mainActivity.getResources().getDimension(R.dimen.card_height));
            int index = cardStrings.indexOf(card);
            params.setMargins(beginningStartMargin + (index * 90), 20, 0, 0);

            ImageView newCard = new ImageView(this.getContext());
            newCard.setElevation(beginningElevation + (index * 4));
            newCard.setAdjustViewBounds(true);
            newCard.setOutlineProvider(ViewOutlineProvider.PADDED_BOUNDS);
            int drawableID = getResources().getIdentifier("@drawable/" + cardStrings.get(index), "drawable", mainActivity.getPackageName());
            //drawableID = R.drawable.ace_clubs;
            newCard.setImageResource(drawableID);


            newCard.setScaleType(ImageView.ScaleType.FIT_XY);
            relativeLayoutHand.addView(newCard, params);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hand, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mView = view;

        if (mHand == null)
            mHand = new Hand();

        if (getArguments() != null) {
            isPlayer = getArguments().getBoolean("is_player");
        }

        txvHandCount = (TextView) view.findViewById(R.id.handCount);
        updateView();

    }

}
