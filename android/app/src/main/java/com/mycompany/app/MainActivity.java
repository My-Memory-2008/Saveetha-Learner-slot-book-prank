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
        
        // 1. Force the app to load your custom activity_main layout instead of Capacitor defaults
        setContentView(R.layout.activity_main);

        // 2. Safely grab the exact components from your custom XML template
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        WebView webView = findViewById(R.id.webview);

        if (swipeRefreshLayout != null && webView != null) {
            // 3. Initialize the core web bridge onto your layout's specific WebView
            this.init(savedInstanceState, webView);

            // 4. Configure the pull down action to trigger page reloads
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });

            // 5. Hide the spinning animation smoothly when Cloudflare finishes loading
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





