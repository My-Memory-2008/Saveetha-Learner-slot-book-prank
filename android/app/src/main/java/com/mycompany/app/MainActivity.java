// package com.mycompany.app;

// import com.getcapacitor.BridgeActivity;

// public class MainActivity extends BridgeActivity {}





package com.mycompany.app; // <-- Ensure this matches the top line of your original file!

import android.os.Bundle;
import android.webkit.WebView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind the layout container to listen for pulling down gestures
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        final WebView webView = findViewById(R.id.webview);

        if (swipeRefreshLayout != null && webView != null) {
            // Trigger a page reload when pulled down
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });

            // Turn off the spinner animation once the Cloudflare website finishes loading
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
