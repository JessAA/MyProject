package com.xebia.hpbook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ConnectivityUtils class
 */
public class ConnectivityUtils {
    /**
     *
     * @param context
     * @return true or false
     * true -> is connected to network
     * false -> is not connected
     */
    public static boolean hasNetworkConnection(Context context) {
        boolean status = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            status = true;
        }
        return status;
    }
}
