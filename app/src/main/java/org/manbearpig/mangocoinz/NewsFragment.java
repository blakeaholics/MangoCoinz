package org.manbearpig.mangocoinz;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.Toast;

public class NewsFragment extends Fragment {

    private WebViewFragment mWebview;

    public NewsFragment(){}
	
	
	/*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        return rootView;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("SwA", "WVF onCreateView");
        View v = inflater.inflate(R.layout.fragment_news, container, false);
            WebView wv = (WebView) v.findViewById(R.id.webView);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("http://www.mangocoinz.com/");
        return v;
    }

}
