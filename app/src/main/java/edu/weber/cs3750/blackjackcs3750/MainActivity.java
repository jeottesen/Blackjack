package edu.weber.cs3750.blackjackcs3750;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.weber.cs3750.blackjackcs3750.DialogFragments.StatsDialogFragment;
import edu.weber.cs3750.blackjackcs3750.DialogFragments.WinDialogFragment;
import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.CardSuits;
import edu.weber.cs3750.blackjackcs3750.Models.CardValues;
import edu.weber.cs3750.blackjackcs3750.Models.Deck;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;

public class MainActivity extends AppCompatActivity implements HandFragment.OnPlayerInteractionListener {

    public static final String PREFS_NAME = "GameStats";

    HandFragment dealerHand;
    HandFragment playerHand;

    private int wins;
    private int losses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        final Deck dealerDeck = new Deck();
        dealerDeck.shuffle();

        dealerHand = new HandFragment();
        dealerHand.addCard(dealerDeck.draw());

        playerHand = new HandFragment();
        playerHand.addCard(dealerDeck.draw());
        playerHand.addCard(dealerDeck.draw());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand)
                .commit();


        Button btnHit = (Button) findViewById(R.id.btnHit);
        Button btnStand = (Button) findViewById(R.id.btnStand);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealerHand.addCard(dealerDeck.draw());
                playerHand.addCard(dealerDeck.draw());
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


