package com.firmanmujahidin.testinterview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mEditText;
    private Button mButtonKlik, mButtonOk, mButtonRiest;
    private WebView mWebView;
    private Spinner mSpinnerConfiguration, mSpinnerColor;

    List<String> listConfig = new ArrayList<>();
    List<String> listColor = new ArrayList<>();

    String mimeType = "text/html";
    String encoding = "UTF-8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWebView = (WebView) findViewById(R.id.mWebView);
        setmWebView();
        mEditText = (EditText) findViewById(R.id.mEt);
        mButtonKlik = (Button) findViewById(R.id.mBtKlik);
        setmButtonKlik();

        mSpinnerConfiguration = (Spinner) findViewById(R.id.mChoiceType);
        setmSpinnerConfiguration();

        mSpinnerColor = (Spinner) findViewById(R.id.mChoiceColor);
        setmSpinnerColor();

        mButtonOk = (Button) findViewById(R.id.buttonOk);
        setmButtonOk();
        mButtonRiest = (Button) findViewById(R.id.buttonReset);
        setmButtonRiest();

    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setmWebView(){
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadData("<html>" +
                "<head>" +
                "<script type=\"text/javascript\">" +

                "function reset(){"+
                    "changeBG(\"white\");"+
                    "changeTeks(\"black\");"+
                "}"+

                "function changeBG(color) {"+
                    "document.getElementById('background').style.backgroundColor = color;"+
                "}"+

                "function changeTeks(color) {"+
                    "document.getElementById('input').style.color = color;"+
                "}"+

                "function myFunction(){"+
                // ini ID dropdown di javascript. Kalo di android kasih id spinner nya seperti apa?
                "var choiceType = document.getElementById('mChoiceType').value;"+
                "var choiceColor = document.getElementById('mChoiceColor').value;"+

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

                "<body id='background'>" +

                "<h2 id='input'> </h2>" +

                //ini dropdown html di webView bisa dengan ID mChoiceType
                "<select id='mChoiceType'>"+
                "<option >Background</option>"+
                "<option >Teks</option>"+
                "</select>"+

                //ini dropdown html di webView bisa dengan ID mChoiceColor
                "<select id='mChoiceColor'>"+
                "<option >Red</option>"+
                "<option >Green</option>"+
                "<option >Yellow</option>"+
                "</select>"+

                "</select>"+
                "</body>" +
                "</html>",mimeType,encoding);
    }

    public void setmButtonKlik(){
        mButtonKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText = mEditText.getText().toString();
                String javascript = "javascript: document.getElementById('input').innerHTML = '"+editText+"'; console.log(\"javascript function called input from android\");";
                mWebView.loadUrl(javascript);

            }
        });
    }

    public void setmSpinnerConfiguration(){

        listConfig.add("Background");
        listConfig.add("Teks");
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listConfig);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerConfiguration.setAdapter(adapter1);



        /* mSpinnerConfiguration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String urlName = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(MainActivity.this, ""+urlName, Toast.LENGTH_SHORT).show();



                String urlValue = "javascript document.getElementById('mChoiceType').innerHTML = mChoiceColor  ";
                switch (urlName) {
                    case "Background":
                        urlValue = "javascript : document.getElementById('mChoiceType').innerHTML = changeBG(choiceColor) ";
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
//                            mWebView.evaluateJavascript("javascript : changeBG(color)", null);
                        Toast.makeText(MainActivity.this, "BACKGROUND", Toast.LENGTH_SHORT).show();
                        break;

                    case "Teks":
                        urlValue = "javascript :document.getElementById('mChoiceType').innerHTML = changeTeks(choiceColor);";
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
//                            mWebView.evaluateJavascript("javascript : changeTeks(color)", null);
                        Toast.makeText(MainActivity.this, "TEKS", Toast.LENGTH_SHORT).show();
                        break;
                }
                mWebView.loadUrl(urlValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }

    public void setmSpinnerColor(){

        listColor.add("Red");
        listColor.add("Green");
        listColor.add("Yellow");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listColor);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerColor.setAdapter(adapter2);
       /* mSpinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String urlName = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(MainActivity.this, ""+urlName, Toast.LENGTH_SHORT).show();

                String urlValue = "javascript :document.getElementById('mChoiceType').innerHTML = mChoiceColor;";
                switch (urlName) {
                    case "Red":
                        urlValue = "javascript :document.getElementById('mChoiceType').innerHTML = ;";
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
//                            mWebView.evaluateJavascript("javascript : document.body.style.backgroundColor='red';", null);
//                        }else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT){
//                            mWebView.loadUrl("javascript : document.body.style.color='red';");
//                        }
                        Toast.makeText(MainActivity.this, "RED", Toast.LENGTH_SHORT).show();
                        break;

                    case "Green":
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
//                            mWebView.evaluateJavascript("javascript : document.body.style.backgroundColor='green';", null);
//                        }else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT){
//                            mWebView.loadUrl("javascript : document.body.style.color='green';");
//                        }
                        Toast.makeText(MainActivity.this, "GREEN", Toast.LENGTH_SHORT).show();
                        break;

                    case "Yellow":
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
//                            mWebView.evaluateJavascript("javascript : document.body.style.backgroundColor='yellow';", null);
//                        }else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT){
//                            mWebView.loadUrl("javascript : document.body.style.color='yellow';");
//                        }
                        Toast.makeText(MainActivity.this, "YELLOW", Toast.LENGTH_SHORT).show();
                        break;
                }
                    mWebView.loadUrl(urlValue);
            }


//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            mWebView.evaluateJavascript("document.body.style.backgroundColor=\"green\";document.body.style.color=\"black\"; '"+parent.getItemAtPosition(position).toString()+"'", null);
//        }else if (listConfig.equals("TEXT")){
//            mWebView.loadUrl("javascript:document.body.style.backgroundColor=\"#blue\";document.body.style.color=\"white\";");
//        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


    }


    public  void setmButtonOk(){
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String javascript = "javascript: document.getElementById('input').value; console.log(\"javascript function called button ok android\");";
                String js = "javascript: myFunction(); console.log(\"javascript function called button Ok android\"); ";
                mWebView.loadUrl(js);

            }
        });
    }

    public void setmButtonRiest(){
        mButtonRiest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String javascriptReset = "javascript: reset(); console.log(\"javascript function called reset txt and bg android\");";
                mWebView.loadUrl(javascriptReset);
                mEditText.setText("");
            }
        });
    }


}


