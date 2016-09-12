package edu.weber.cs3750.blackjackcs3750;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.weber.cs3750.blackjackcs3750.DialogFragments.LoseDialogFragment;
import edu.weber.cs3750.blackjackcs3750.DialogFragments.StatsDialogFragment;
import edu.weber.cs3750.blackjackcs3750.DialogFragments.TieDialogFragment;
import edu.weber.cs3750.blackjackcs3750.DialogFragments.WinDialogFragment;
import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.Deck;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "game_stats";

    public int round;

    private MenuItem roundDisplayMenuItem;


    protected Button btnHit;
    protected Button btnStand;

    HandFragment dealerHand;
    HandFragment playerHand;

    private boolean playerWin;
    private int wins;
    private int losses;
    private int ties;

    private Deck currentDeck;
    //private boolean playerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        round = settings.getInt("round", 1);
        wins = settings.getInt("wins", 0);
        losses = settings.getInt("losses", 0);
        ties = settings.getInt("ties", 0);

        if (savedInstanceState != null) {
            return;
        }

        currentDeck = new Deck();

        playerHand = HandFragment.newInstance(true);
        dealerHand = HandFragment.newInstance(false);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand, "dealerHand")
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand, "playerHand")
                .commit();

        deal();


        btnHit = (Button) findViewById(R.id.btnHit);
        btnStand = (Button) findViewById(R.id.btnStand);


        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hit();
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findWinner();
            }
        });

    }


    public void resetGame() {
        round++;
        roundDisplayMenuItem.setTitle("Round " + round + "    ");
        playerHand.removeAllCards();
        dealerHand.removeAllCards();
        playerHand.txvBlackjackOrBust.setText("");
        dealerHand.txvBlackjackOrBust.setText("");
        currentDeck.shuffle();
        deal();
    }


    public void hit() {
        playerHand.addCard(currentDeck.draw());

        if (playerHand.getHandCount() > 21) {
            playerWin = false;
            findWinner();
        }
    }


    public void deal() {
        //dealerHand.addCard(new Card(CardValues.TEN, CardSuits.HEARTS));//new, for testing, Geese
        dealerHand.addCard(currentDeck.draw());//uncomment for real game

        playerHand.addCard(currentDeck.draw());

        //Card dealersFacedownCard = new Card(CardValues.ACE, CardSuits.HEARTS);  //new, for testing, Geese
        Card dealersFacedownCard = currentDeck.draw();  //uncomment for real game

        /*  get the Card's value before it gets put faceDown and set to zero  */
        int faceDownValue = dealersFacedownCard.getValue();   //new, Geese

        dealersFacedownCard.setFacedown(true);
        dealerHand.addCard(dealersFacedownCard);

        playerHand.addCard(currentDeck.draw());

        /*  if the Dealer's faceup card is an Ace or has a value of 10,
            then "peek" at the facedown card to determine if Dealer has natural Blackjack already.
            new, Geese
         */
        if (dealerHand.getCard(0).getValue() == 11 || dealerHand.getCard(0).getValue() == 10) {
            if ((dealerHand.getHandCount() + faceDownValue) == 21) {
                findWinner();  //player has no chance to hit or stand, because the Dealer hit natural blackjack
                return;  //without this return, after a push, the Push dialog will always flash before the Lose dialog
            }
        }

        checkForBlackjack();
    }

    public void checkForBlackjack() {
        if (playerHand.getHandCount() == 21) {
            playerWin = true;
            findWinner();
        }
    }

    public void findWinner() {
        //boolean playerTies = false;  appears to be unused
        dealerHand.getCard(1).setFacedown(false);  //turn Dealer's second card face up
        dealerHand.updateView();

        while (dealerHand.getHandCount() < 17) {
            dealerHand.addCard(currentDeck.draw());
        }
        if (playerHand.getHandCount() == 21 && dealerHand.getHandCount() != 21) {
            playerWin = true;
        } else if (playerHand.getHandCount() > 21 || (dealerHand.getHandCount() <= 21 &&
                playerHand.getHandCount() < dealerHand.getHandCount())) {
            playerWin = false;
        } else if (playerHand.getHandCount() < 21 && (playerHand.getHandCount() > dealerHand.getHandCount())) {
            playerWin = true;
        } else if (playerHand.getHandCount() < 21 && dealerHand.getHandCount() > 21) {
            playerWin = true;
        } else {
            TieDialogFragment dialogFragment = new TieDialogFragment();
            dialogFragment.show(getFragmentManager(), "TIE_DIALOG");
            ties++;
            return;  //avoid unwanted dialogs after the push
        }

        if (playerWin) {
            WinDialogFragment dialogFragment = new WinDialogFragment();
            dialogFragment.show(getFragmentManager(), "WIN_DIALOG");
            wins++;

        } else {
            LoseDialogFragment dialogFragment = new LoseDialogFragment();
            dialogFragment.show(getFragmentManager(), "LOSE_DIALOG");
            losses++;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        roundDisplayMenuItem = menu.getItem(0);
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
            case R.id.newGame:
                resetGame();
                return true;
            case R.id.gameRules:
                /*GameRulesDialogFragment gameRulesDialogFragment = new GameRulesDialogFragment();
                gameRulesDialogFragment.show(getFragmentManager(), "GAME_RULES");*/
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.wholeContainer, new GameRulesFragment(), "gameRules")
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showStatsDialog() {
        // Create an instance of the Dialog Fragment using the newInstance method so the arguments are bundled for us
        StatsDialogFragment dialogFragment = StatsDialogFragment.newInstance(wins, losses, ties);
        dialogFragment.show(getFragmentManager(), "STAT_DIALOG");
    }

    public void clearStats() {
        round = 1;
        wins = 0;
        losses = 0;
        ties = 0;
        roundDisplayMenuItem.setTitle("Round " + round + "    ");
    }

    @Override
    protected void onStop() {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("wins", wins);
        editor.putInt("losses", losses);
        editor.putInt("ties", ties);
        editor.putInt("round", round);

        // Commit the edits!
        editor.apply();
    }
}
