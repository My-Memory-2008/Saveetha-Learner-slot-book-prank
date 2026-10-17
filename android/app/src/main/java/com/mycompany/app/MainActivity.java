// package com.mycompany.app;

// import com.getcapacitor.BridgeActivity;

// public class MainActivity extends BridgeActivity {}







package com.mycompany.app;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind the native Android swipe container to our instance layout
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        final WebView webView = findViewById(R.id.webview);

        if (swipeRefreshLayout != null && webView != null) {
            // Configure the trigger action to reload your live Cloudflare site
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });

            // Listen to the web page lifecycle to spin down the animation once loaded
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
