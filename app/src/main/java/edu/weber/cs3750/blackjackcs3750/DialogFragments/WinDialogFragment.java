package edu.weber.cs3750.blackjackcs3750.DialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import edu.weber.cs3750.blackjackcs3750.MainActivity;

public class WinDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        // Build the Dialog and set the values that it will display
        builder.setMessage("You're a winner!")
                .setCancelable(false)
                .setTitle("Yay, you won!")
                .setPositiveButton("Play Again?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity) getActivity()).resetGame();
                    }
                })
                .setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // quit the app
                        getActivity().finish();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}