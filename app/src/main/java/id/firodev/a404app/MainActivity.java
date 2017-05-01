package id.firodev.a404app;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cekKoneksi();

    }

    private void cekKoneksi() {
        boolean jikaKonek = ConnectivityReceiver.isConnected();
        munculSnackBar(jikaKonek);
    }

    private void munculSnackBar(boolean jikaKonek) {
        String message;
        int color;
        if (jikaKonek){
            message = "Sip! Tersambung ke Internet";
            color = Color.WHITE;
        } else {
            message = "Maaf, Coba Cek Koneksi Internetnya";
            color = Color.YELLOW;
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView)sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
        
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        munculSnackBar(isConnected);
    }
}
