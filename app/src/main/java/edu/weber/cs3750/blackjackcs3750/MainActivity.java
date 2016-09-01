package edu.weber.cs3750.blackjackcs3750;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.weber.cs3750.blackjackcs3750.Models.Card;
import edu.weber.cs3750.blackjackcs3750.Models.CardSuits;
import edu.weber.cs3750.blackjackcs3750.Models.CardValues;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;

public class MainActivity extends AppCompatActivity implements HandFragment.OnPlayerInteractionListener {


    HandFragment dealerHand;
    HandFragment playerHand;

    private Button btnHit;
    private Button btnStand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        dealerHand = new HandFragment();
        dealerHand.addCard(new Card(CardValues.ACE, CardSuits.CLUBS));

        playerHand = new HandFragment();
        playerHand.addCard(new Card(CardValues.EIGHT, CardSuits.CLUBS));
        playerHand.addCard(new Card(CardValues.NINE, CardSuits.SPADES));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand)
                .commit();


        btnHit = (Button) findViewById(R.id.btnHit);
        btnStand = (Button) findViewById(R.id.btnStand);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealerHand.addCard(new Card(CardValues.TEN, CardSuits.SPADES));
                playerHand.addCard(new Card(CardValues.SEVEN, CardSuits.HEARTS));
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findWinner();
            }
        });

    }

    public void findWinner()
    {
        //TODO find winner and display result as dialog
        Toast toast = Toast.makeText(this, "TODO find winner!", Toast.LENGTH_SHORT);
        toast.show();
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
}
