package edu.weber.cs3750.blackjackcs3750;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

import edu.weber.cs3750.blackjackcs3750.DialogFragments.StatsDialogFragment;
import edu.weber.cs3750.blackjackcs3750.DialogFragments.WinDialogFragment;
import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.Deck;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;

public class MainActivity extends AppCompatActivity implements HandFragment.OnPlayerInteractionListener {

    public static final String PREFS_NAME = "GameStats";

    Deck deck;
    Queue<Card> discardQueue = new LinkedList<>();

    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    protected Button btnHit;
    protected Button btnStand;
    protected Button btnDeal; //button added by Geese

    HandFragment dealerHand;
    HandFragment playerHand;

    private int round;
    private int wins;
    private int losses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MainActivity.MODE_PRIVATE);
        editor = prefs.edit();

        deck = new Deck();

       // Log.d("debug", "deck size: " + deck.deckSize());
       // Log.d("debug", "card 0: " + deck.deck.get(0).getValue());

        if (savedInstanceState != null) {
            return;
        }

        dealerHand = new DealerHandFragment();
        //dealerHand.addCard(new Card(CardValues.BACK, CardSuits.BACK));
       // dealerHand.addCard(new Card(CardValues.ACE, CardSuits.HEARTS));


    playerHand = new PlayerHandFragment();
    //playerHand.addCard(new Card(CardValues.EIGHT, CardSuits.CLUBS));
    //playerHand.addCard(new Card(CardValues.NINE, CardSuits.DIAMONDS));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand, "dealerHand")
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand, "playerHand")
                .commit();


       // Log.d("debug", "onCreate: dealerHand 0 : " + dealerHand.mHand.handCards.get(0).getValue());


        btnHit = (Button) findViewById(R.id.btnHit);
        btnStand = (Button) findViewById(R.id.btnStand);
        btnDeal = (Button) findViewById(R.id.btnDeal);

        btnDeal.setOnClickListener(new View.OnClickListener() { //Geese
            @Override
            public void onClick(View view) {
                DealerHandFragment dealerHandFragment = (DealerHandFragment)getSupportFragmentManager().findFragmentByTag("dealerHand");
                PlayerHandFragment playerHandFragment = (PlayerHandFragment)getSupportFragmentManager().findFragmentByTag("playerHand");
                editor.putInt("round", 1).apply();
                //view.setEnabled(false);
                deck.initialize();
                deck.shuffle();
                dealFirstCards(new DealerHandFragment().getClass());
                dealFirstCards(new PlayerHandFragment().getClass());
                dealerHandFragment.setFirstCardFaceUp(false);  //could be set to true when clicking "Stand"
                dealerHandFragment.updateView();
                playerHandFragment.updateView();
            }
        });

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deck.dealCard(dealerHand.mHand);
                deck.dealCard(playerHand.mHand);
                dealerHand.updateView();
                playerHand.updateView();
                /*dealerHand.addCard(new Card(CardValues.KING, CardSuits.SPADES));
                playerHand.addCard(new Card(CardValues.SEVEN, CardSuits.HEARTS));*/
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findWinner();
            }
        });

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        wins = settings.getInt("wins", 0);
        losses = settings.getInt("losses", 0);

    }

    /*
    This method gets called by each HandFragment after its View is created.  That way the HandFragment's hand
    is sure to actually exist before the first cards are dealt to it.  (avoiding Null Pointer)  --Geese
     */
    public void dealFirstCards(Class theClass){
        if (dealerHand.mHand != null && theClass.toString().contains("Dealer")) {
            deck.dealCard(dealerHand.mHand, 2);
        }
        if (playerHand.mHand != null && theClass.toString().contains("Player"))
            deck.dealCard(playerHand.mHand, 2);
    }


    public void resetGame() {
        // TODO reset deck and player hands
    }

    public void findWinner() {
        //TODO find winner and display result as dialog
        WinDialogFragment dialogFragment = new WinDialogFragment();
        dialogFragment.show(getFragmentManager(), "WIN_DIALOG");
    }

    @Override
    public void handEndStatus(HandStatus status) {
        String toastMessage = "";
        if (status == HandStatus.BUST)
            toastMessage = "Bust!";
        else if (status == HandStatus.NATURAL)
            toastMessage = "BlackJack!";

        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.showStats:
                showStatsDialog();
                return true;
            case R.id.clearStats:
                clearStats();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showStatsDialog() {
        // Create an instance of the Dialog Fragment using the newInstance method so the arguments are bundled for us
        StatsDialogFragment dialogFragment = StatsDialogFragment.newInstance(wins, losses);
        dialogFragment.show(getFragmentManager(), "STAT_DIALOG");
    }

    public void clearStats() {
        wins = 0;
        losses = 0;
    }

    @Override
    protected void onStop() {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("wins", wins);
        editor.putInt("losses", losses);

        // Commit the edits!
        editor.apply();
    }
}


