package com.example.qrc;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
    Context mContext;
    Activity mActivity;

    JavaScriptInterface(Context c, Activity activity) {
        mContext = c;
        mActivity = activity;
    }

    @JavascriptInterface
    public void callFromJS() {
        Toast.makeText(mContext, "JavaScript interface call", Toast.LENGTH_LONG).show();
    }

    // Show a toast from the web page
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public int getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    @JavascriptInterface
    public void closeKeyboard() {
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
