package sg.edu.rp.c346.id19037610.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    WebView wvWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        wvWebsite = findViewById(R.id.webViewWebsite);
        wvWebsite.setWebViewClient(new WebViewClient());

        WebSettings webSettings = wvWebsite.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        Intent intent = getIntent();

        String web = intent.getStringExtra("choice");

        wvWebsite.loadUrl(web);
    }
}
