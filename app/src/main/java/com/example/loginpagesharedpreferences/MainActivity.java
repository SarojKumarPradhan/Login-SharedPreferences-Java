package com.example.loginpagesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ed1 ,ed2 ,ed3 ,ed4 ;
    Button b1;
    TextView textView;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        b1 = (Button) findViewById(R.id.button1);

//        textView = (TextView) findViewById(R.id.textView);

// This Code is for when open app again after login it check app already login or nor if login
// Then it automatically go to the home page without going to login page
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String nameget = sharedPreferences.getString(Name,null);
        String phoneget = sharedPreferences.getString(Phone,null);
        String emailget = sharedPreferences.getString(Email,null);
        String passwordget = sharedPreferences.getString(Password,null);

        if (nameget != null && phoneget != null && emailget != null && passwordget != null)
        {
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
        }
        //*****************************************************

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                String email = ed3.getText().toString();
                String password = ed4.getText().toString();

                if (name.isEmpty()) {
                    ed1.setError("Name cannot be empty.");
                    ed1.requestFocus();
                    return;
                }
                if (phone.isEmpty()) {
                    ed2.setError("Phone Number cannot be empty.");
                    ed2.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    ed3.setError("Email cannot be empty.");
                    ed3.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    ed4.setError("Password cannot be empty.");
                    ed4.requestFocus();
                    return;
                }else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(Name, name);
                    editor.putString(Phone, phone);
                    editor.putString(Email, email);
                    editor.putString(Password,password);
                    editor.putString("display",email);
                    editor.putString("display2",password);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    startActivity(intent);
                }

            }
        });
    }
}