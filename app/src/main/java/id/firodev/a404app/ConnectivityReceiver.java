package id.firodev.a404app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ariaseta on 30/04/2017.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null){
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aktifNetwork = cm.getActiveNetworkInfo();
        return aktifNetwork != null && aktifNetwork.isConnectedOrConnecting();
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
