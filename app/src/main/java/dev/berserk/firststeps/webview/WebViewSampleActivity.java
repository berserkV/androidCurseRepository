package dev.berserk.firststeps.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;

public class WebViewSampleActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.progressBar2)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_sample);

        ButterKnife.bind(this);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.loadUrl("https://kissmanga.com/");
    }
}
