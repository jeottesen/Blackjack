package edu.weber.cs3750.blackjackcs3750;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.weber.cs3750.blackjackcs3750.Models.Deck;
import edu.weber.cs3750.blackjackcs3750.Models.HandStatus;

public class MainActivity extends AppCompatActivity implements HandFragment.OnPlayerInteractionListener {


    HandFragment dealerHand;
    HandFragment playerHand;
    private Deck currentDeck;
    private Button btnHit;
    private Button btnStand;
    private Button btnNewGame;
    private boolean playerTurn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        currentDeck = new Deck();
        if (savedInstanceState != null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("isPlayer", true);
        playerHand = new HandFragment();
        intent.putExtra("isPlayer",false);
        dealerHand = new HandFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.dealerHand, dealerHand)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.playerHand, playerHand)
                .commit();

        deal();

        btnHit = (Button) findViewById(R.id.btnHit);
        btnStand = (Button) findViewById(R.id.btnStand);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hit();
            }
        });
        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerTurn == true){
                    playerTurn = false;
                    hit();
                }
                if(dealerHand.getHandCount() > 17) {
                    findWinner();
                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHit.setClickable(true);
                playerHand.removeAllCards();
                dealerHand.removeAllCards();
                currentDeck.shuffle();
                deal();
            }
        });
    }

    public void hit(){
        if(playerTurn == true) {
            playerHand.addCard(currentDeck.getTopCard());
            playerTurn = false;
        }
        if(playerTurn == false) {
            if (dealerHand.getHandCount() < 17)
                dealerHand.addCard(currentDeck.getTopCard());
            playerTurn = true;
        }
    }

    public void deal(){
        dealerHand.addCard(currentDeck.getTopCard());
        playerHand.addCard(currentDeck.getTopCard());
        dealerHand.addCard(currentDeck.getTopCard());
        playerHand.addCard(currentDeck.getTopCard());
    }
    public void findWinner()
    {
        String winnerName = "";
        if(dealerHand.getHandCount() < 22 && playerHand.getHandCount() < 22){
            if(dealerHand.getHandCount() >= playerHand.getHandCount()){
                winnerName = "Dealer Wins!!";
            }
            else{
                winnerName = "Player Wins!!";
            }
        }
        else if(dealerHand.getHandCount() > 21){
            winnerName = "Player Wins!!";
        }
        else {
            winnerName = "Dealer Wins!!";
        }
        btnHit.setClickable(false);
        Toast toast = Toast.makeText(this, winnerName, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void handEndStatus(HandStatus status) {
        String toastMessage = "";
        if (status == HandStatus.BUST)
            toastMessage = "Bust!";
        else if (status == HandStatus.NATURAL)
            toastMessage = "BlackJack!";
        findWinner();
//        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
//        toast.show();
        if(dealerHand.getHandCount() >= 21 || playerHand.getHandCount() >= 21){
            btnHit.setClickable(false);
        }
    }
}
