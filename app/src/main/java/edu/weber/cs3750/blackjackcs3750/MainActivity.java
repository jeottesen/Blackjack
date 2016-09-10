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
import edu.weber.cs3750.blackjackcs3750.DialogFragments.WinDialogFragment;
import edu.weber.cs3750.blackjackcs3750.Models.Deck;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "game_stats";

    HandFragment dealerHand;
    HandFragment playerHand;


    private int wins;
    private int losses;

    private Deck currentDeck;
    private boolean playerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        currentDeck = new Deck();

        playerHand = HandFragment.newInstance(true);
        dealerHand = HandFragment.newInstance(false);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand)
                .commit();

        deal();

        final Button btnHit = (Button) findViewById(R.id.btnHit);
        Button btnStand = (Button) findViewById(R.id.btnStand);


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


        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        wins = settings.getInt("wins", 0);
        losses = settings.getInt("losses", 0);

    }


    public void resetGame() {
        playerHand.removeAllCards();
        dealerHand.removeAllCards();
        currentDeck.shuffle();
        deal();
    }


    public void hit() {
        playerHand.addCard(currentDeck.draw());

        //if his
        if (playerHand.getHandStatus() != HandStatus.SAFE) {
            findWinner();
        }
    }



    public void deal() {
        dealerHand.addCard(currentDeck.draw());
        playerHand.addCard(currentDeck.draw());
        dealerHand.addCard(currentDeck.draw());
        playerHand.addCard(currentDeck.draw());
    }

    public void findWinner() {
        boolean playerWin;

        if(dealerHand.getHandCount() < 17) {
            dealerHand.addCard(currentDeck.draw());
            findWinner();
        }

        if (dealerHand.getHandCount() < 22 && playerHand.getHandCount() < 22) {
            if (dealerHand.getHandCount() >= playerHand.getHandCount()) {
                playerWin = false;
            } else {
                playerWin = true;
            }
        } else if (dealerHand.getHandCount() > 21) {
            playerWin = true;
        } else {
            playerWin = false;
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


