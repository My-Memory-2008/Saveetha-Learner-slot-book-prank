package com.mycompany.app;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Safely grab Capacitor's running WebView instance
        final WebView webView = this.bridge.getWebView();

        if (webView != null) {
            // 2. Programmatically generate a SwipeRefreshLayout container at runtime
            swipeRefreshLayout = new SwipeRefreshLayout(this);
            swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));

            // 3. Detach the WebView from its default structural location and nest it inside our refresher
            ViewGroup parentView = (ViewGroup) webView.getParent();
            if (parentView != null) {
                int index = parentView.indexOfChild(webView);
                parentView.removeView(webView);
                swipeRefreshLayout.addView(webView);
                parentView.addView(swipeRefreshLayout, index);
            }

            // 4. Configure the pull-down action to reload your Cloudflare site
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });

            // 5. Turn off the spinner wheel animation smoothly when loading completes
            webView.setWebViewClient(new android.webkit.WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }
}


// package com.mycompany.app;

// import com.getcapacitor.BridgeActivity;

// public class MainActivity extends BridgeActivity {}





