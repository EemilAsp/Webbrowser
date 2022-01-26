package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public WebView wb;
    EditText urlip;
    ImageButton refr;
    String url;
    Button initialize, shoutout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wb = (WebView) findViewById(R.id.webView);
        urlip = (EditText) findViewById(R.id.addUrl);
        refr = (ImageButton) findViewById(R.id.refresh);
        initialize = (Button) findViewById(R.id.initialize);
        shoutout = (Button)findViewById(R.id.shtout);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);

        initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiaLize();
            }
        });
        shoutout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoutOut();
            }
        });

        refr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlip.getText().toString();
                wb.loadUrl(url);
            }});

        urlip.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    url = urlip.getText().toString();
                    if(url.equals("http://index.html")){
                        wb.loadUrl("file:///android_asset/index.html");
                    }else{
                    wb.loadUrl(url);
                }}
                return false;
            }
        });
    }
    public void onNextPressed(View view){
        if(wb.canGoForward()){wb.goForward();}
    }

    public void onBackPressed(View view){
        if(wb.canGoBack()){
            wb.goBack();
            wb.clearHistory();
        }
    }

    public void initiaLize(){
        wb.evaluateJavascript("javascript:initialize()", null);
    }
    public void shoutOut(){
        wb.evaluateJavascript("javascript:shoutOut()", null);
    }
}