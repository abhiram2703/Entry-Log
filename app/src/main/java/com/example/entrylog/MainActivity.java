package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    AppCompatButton bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences preferance=getSharedPreferences("login",MODE_PRIVATE);
        String username=preferance.getString("user",null);
        if (username!=null)
        {
            Intent in=new Intent(getApplicationContext(), LogEntry.class);
            startActivity(in);
        }
        et1=(EditText) findViewById(R.id.unameet);
        et2=(EditText) findViewById(R.id.passet);
        bt1=(AppCompatButton) findViewById(R.id.loginbtn);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if(s1.equals("user")&&s2.equals("1234"))
                {
                    SharedPreferences preferance=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferance.edit();
                    editor.putString("user","admin");
                    editor.apply();
                    Intent in=new Intent(getApplicationContext(), LogEntry.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}