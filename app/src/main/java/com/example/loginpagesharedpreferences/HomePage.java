package com.example.loginpagesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomePage extends AppCompatActivity {

    Button b2,b3;
    TextView textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);



        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String display = sharedPreferences.getString("display","");
        String display2 = sharedPreferences.getString("display2","");
//        String displayName = sharedPreferences.getString(MainActivity.Name,"");
//        textView2.setText(displayName);
        textView2.setText(display+display2);


        //********This Code is For Get Post Method************************
        String url = "https://costumer1.portmap.io/login";

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formbody = new FormBody.Builder().add("username",display).add("password",display2).build();
        Request request = new Request.Builder().url(url).post(formbody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String myResponse = response.body().string();
                    HomePage.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            textView3.setText(myResponse);
                        }
                    });
                }
            }
        });
        //*****************************************************************



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });
    }

    //This code is used for direct back to home screen from this activity........
    @Override
    public void onBackPressed() {
        //this is very good code for directly back to home page with out any alert msg*******************
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
