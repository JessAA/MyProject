package com.xebia.hpbook.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.xebia.hpbook.R;

/**
 * BookAlertDialog class
 */
public class BookAlertDialog {

    private Context context;

    /**
     * @param context
     */
    public BookAlertDialog(Context context) {

        this.context = context;

    }

    /**
     * Show an alert dialog about network connection
     */
    public void showConnectionDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder
                .setMessage(R.string.recheck_wifi_connection)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    /**
     * show an alert dialog about books that needs to be selected
     */
    public void showSelectBookDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder
                .setMessage(R.string.choose_a_book)
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
