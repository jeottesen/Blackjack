package edu.weber.cs3750.blackjackcs3750.DialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

public class StatsDialogFragment extends DialogFragment {

    // Will return an instance of this fragment with the wins and losses passed in
    public static StatsDialogFragment newInstance(int wins, int losses, int ties) {
        StatsDialogFragment statsDialogFragment = new StatsDialogFragment();

        // Put the wins and losses variables into an android bundle objece
        Bundle args = new Bundle();
        args.putInt("wins", wins);
        args.putInt("losses", losses);
        args.putInt("ties", ties);

        // set the arguments to the bundle we just made so we can access the variables later
        statsDialogFragment.setArguments(args);

        return statsDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // pull the two variables from the arguments bundle so we can display them
        int wins = getArguments().getInt("wins");
        int losses = getArguments().getInt("losses");
        int ties = getArguments().getInt("ties");

        String message = "Wins: " + wins + "\n";
        message += "Losses: " + losses + "\n";
        message += "Ties(Pushes): " + ties;

        // Build the Dialog and set the values that it will display
        builder.setMessage(message)
                .setCancelable(false)
                .setTitle("Win/Lose/Push Statistics")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}