package net.gotev.hostmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Monitors connectivity changes and starts or stops the HostMonitor accordingly.
 * @author gotev (Aleksandar Gotev)
 */
public class HostMonitorConnectivityReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "HostMonitorCR";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Logger.debug(LOG_TAG, "connection available :)");
            HostMonitor.setConnectionType(networkInfo.getType());
            HostMonitor.start();

        } else {
            Logger.debug(LOG_TAG, "connection unavailable :(");
            HostMonitor.setConnectionType(-1);
            HostMonitor.stop(true);
        }
    }
}
