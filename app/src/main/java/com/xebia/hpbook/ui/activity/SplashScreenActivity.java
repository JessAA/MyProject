package com.xebia.hpbook.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;

import com.xebia.hpbook.R;
import com.xebia.hpbook.utils.ConnectivityUtils;

/**
 * SplashScreenActivity class
 */
public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        if (ConnectivityUtils.hasNetworkConnection(SplashScreenActivity.this)) {

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage(R.string.wifi_connection)
                    .setPositiveButton(R.string.wifi_check, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                            finish();
                            System.exit(1);
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
