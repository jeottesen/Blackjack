package edu.weber.cs3750.blackjackcs3750;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    protected Hand mHand;
    private TextView txvCurrentHand;
    private TextView txvHandCount;

    private OnPlayerInteractionListener mListener;
    private String whoseHand; //Gisela

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

/*    protected void updateView() {
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
    }*/


    protected void updateView() {
        ArrayList<String> cardStrings = mHand.toStringArrayList();
        //Log.d("debug", "updateView: cardStrings.size " + cardStrings.size());
        drawCardImages(cardStrings, this.getClass());
        //txvCurrentHand.setText(String.valueOf(cardStrings.get(0)));
        String handCountText;

        if(mHand.getCardCount() < 21)
            handCountText = String.valueOf(mHand.getCardCount());
        else if (mHand.getCardCount() == 21)
            handCountText = "BlackJack";
        else
            handCountText = "Bust";

        txvHandCount.setText(handCountText);
    }

    private void drawCardImages(ArrayList<String> cardStrings, Class thisClass) {
        int beginningStartMargin = 60;
        int beginningElevation = 4;
        MainActivity mainActivity = (MainActivity)getActivity();
        RelativeLayout relativeLayoutHand = (RelativeLayout)getView().findViewById(R.id.rel_layout_hand);

        for (String card : cardStrings) {
            //each card has to have a new instance of LayoutParams
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int)mainActivity.getResources().getDimension(R.dimen.card_width),
                            (int)mainActivity.getResources().getDimension(R.dimen.card_height));
            int index = cardStrings.indexOf(card);
            params.setMargins(beginningStartMargin + (index * 90), 20, 0, 0);
            ImageView newCard = new ImageView(getContext());
            newCard.setElevation(beginningElevation + (index * 4));
            newCard.setAdjustViewBounds(true);
            newCard.setOutlineProvider(ViewOutlineProvider.BOUNDS);
            int drawableID = mainActivity.getResources().getIdentifier(cardStrings.get(index), "drawable", mainActivity.getPackageName());
            if (thisClass.toString().contains("Dealer") && index == 0)
                newCard.setImageResource(R.drawable.card_back);
            else
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
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_hand, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(mHand == null)
            mHand = new Hand();

        //MainActivity mainActivity = (MainActivity)getActivity();
        //mainActivity.dealFirstCards(this.getClass());  //this way the first cards are dealt AFTER the Hand exists. (Geese)

        //txvCurrentHand = (TextView) view.findViewById(R.id.currentHand);
        txvHandCount = (TextView) view.findViewById(R.id.handCount);

        //updateView();
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
