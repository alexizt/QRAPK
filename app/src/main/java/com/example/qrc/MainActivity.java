package com.example.qrc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import 	android.graphics.drawable.ColorDrawable;
import 	android.graphics.Color;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    private AlertDialog alertDialog=null;
    Context context=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context =this;
        super.onCreate(savedInstanceState);


        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Prepare Alert
        prepareAlert();

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#076dbb")));
        getSupportActionBar().hide(); // hide the title bar

        webview = findViewById(R.id.webView);
        webview.setBackgroundColor(Color.BLACK);
        webview.addJavascriptInterface(new JavaScriptInterface(this), "AndroidInterface"); // To call methods in Android from using js in the html, AndroidInterface.showToast, AndroidInterface.getAndroidVersion etc
        webview.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onReceivedError( WebView view, int errorCode, String description, String failingUrl)
                                     {
                                         //String msg="description error" + description;
                                         String msg=getString(R.string.CANT_CONNECT);
                                         System.out.println("description error" + description);
                                         Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                         //view.setVisibility( View.GONE );
                                         view.loadUrl( "file:///android_asset/index.html" );
                                     }
                                 }
        );

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl( "file:///android_asset/index.html" );
        //webview.loadUrl("http://192.168.1.200:8081/Prelievo");
    }

    private void prepareAlert() {
        AlertDialog.Builder alertDialogBuilder = null;
        alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle("No connection");
        // set dialog message
        alertDialogBuilder
                .setMessage("No connection, Retry")
                .setCancelable(true);
        // create alert dialog
        alertDialog = alertDialogBuilder.create();
    }

/*
    private boolean isNetworkConnected(String url) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            alertDialog.show();
            return false;
        } else
            webview.loadUrl( "javascript:window.location.reload( true )" );
        return true;
    }
*/

}
