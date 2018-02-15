package com.firmanmujahidin.testinterview;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private EditText mEditText;
    private Button mButtonKlik, mButtonOk, mButtonRiest;
    private WebView mWebView;
    private Spinner mSpinnerConfiguration, mSpinnerColor;

    String htmlText = "<P>Kalau sudah di klik di sini tampilan teks nya</p>";
    String mimeType = "text/html";
    String encoding = "utf-8";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.mWebView);
        mEditText = (EditText) findViewById(R.id.mEt);
        mButtonKlik = (Button) findViewById(R.id.mBtKlik);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(Uri.parse("file:///android_asset/index.html").toString());
        mWebView.loadData("<html>" +
                "<head>" +
                "<script>" +
                "function changeBG(color) {"+
                    "document.body.style.backgroundColor = color;"+
                "}"+
                "function changeTeks(color) {"+
                    "document.getElementById('input').style.color = color;"+
                "}"+
                "function myFunction(){"+
                    "var choiceType = document.getElementById('choice-type').value;"+
                    "var choiceColor = document.getElementById('choice-color').value;"+
                    "if (choiceType) {"+
                        "if (choiceType == 'Background'){"+
                            "changeBG(choiceColor);"+
                        "}"+
                        "if (choiceType == 'Teks'){"+
                            "changeTeks(choiceColor);"+
                        "}"+
                    "}"+
                "}"+
                "</script>" +
                "</head>" +
                "<body id='baground'>" +
                "<p><span id='input'></span></p>" +
                "</body>" +
                "</html>","text/html","UTF-8");


        mButtonKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText = mEditText.getText().toString();
                String javascript = "javascript: document.getElementById('input').innerHTML = '"+editText+"';void(0);";
                mWebView.loadUrl(javascript);

                if (editText.matches("")) {
                    mEditText.setError("Message is Empty");
                }else {
                    mEditText.setText("");
                }
            }
        });
        mSpinnerConfiguration = (Spinner) findViewById(R.id.mSpinnerConfiguration);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.config, android.R.layout.simple_list_item_1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerConfiguration.setAdapter(adapter1);
        mSpinnerConfiguration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                String item1 = mSpinnerConfiguration.getSelectedItem().toString();
                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),"" + item1, Toast.LENGTH_LONG).show();

               if (item1 != "Background"){
//                    Toast.makeText(MainActivity.this, "Background", Toast.LENGTH_SHORT).show();
//                    String background = "javascript : document.getElementById('choice-type').innerHTML = '"+mSpinnerConfiguration.getSelectedItem()+"'; changeBG(color);";

                   mWebView.setWebViewClient(new WebViewClient(){
                       public void onPageFinished(WebView webView, String url){
                           String background = "javascript :changeBG(color)";
                           mWebView.loadUrl(background);
                       }
                   });
                }
                if (item1 != "Teks"){
//                    Toast.makeText(MainActivity.this, "Teks", Toast.LENGTH_SHORT).show();
//                    String teks = "javascript : document.getElementById('choice-color').innerHTML = '"+mSpinnerConfiguration.getSelectedItem()+"'; changeTeks(color);";

                    mWebView.setWebViewClient(new WebViewClient(){
                        public void onPageFinished(WebView webView, String url){
                            String teks = "javascript : changeTeks(color)";
                            mWebView.loadUrl(teks);
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mWebView.loadData(htmlText,mimeType,encoding);
            }
        });


        mSpinnerColor = (Spinner) findViewById(R.id.mSpinnerColor);
        @SuppressLint("ResourceType")
        final ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<>(this,
                R.array.color, android.R.layout.simple_list_item_2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item2 = mSpinnerColor.getSelectedItem().toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),"" + item2, Toast.LENGTH_LONG).show();

                if (item2 != "Red"){
                    Toast.makeText(MainActivity.this, "colorRed", Toast.LENGTH_SHORT).show();
//                    String red ="javascript: document.getElementById('input').style.color = red;";
//                    mWebView.loadUrl(red);
                }
                if (item2 != "Green"){
                    Toast.makeText(MainActivity.this, "colorGreen", Toast.LENGTH_SHORT).show();
//                    String green ="javascript: document.getElementById('input').style.color = green;";
//                    mWebView.loadUrl(green);
                }
                if (item2 != "Yellow"){
                    Toast.makeText(MainActivity.this, "colorYellow", Toast.LENGTH_SHORT).show();
//                    String yellow ="javascript: document.getElementById('input').style.color = yellow;";
//                    mWebView.loadUrl(yellow);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mWebView.loadData(htmlText,mimeType,encoding);
            }
        });




        mButtonOk = (Button) findViewById(R.id.buttonOk);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String javascript = "javascript:myFunction()";
                mWebView.loadUrl(javascript);
            }
        });

        mButtonRiest = (Button) findViewById(R.id.buttonReset);
        mButtonRiest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.clearView();
            }
        });

    }
}


