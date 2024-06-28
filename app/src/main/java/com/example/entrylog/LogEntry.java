package com.example.entrylog;

import android.content.Intent;
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

public class LogEntry extends AppCompatActivity {
    EditText getName,getAdmNo,getSysNo,getDep;
    AppCompatButton addbtn,logoubtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_entry);
        getName=(EditText) findViewById(R.id.getName);
        getAdmNo=(EditText) findViewById(R.id.getAdmNo);
        getSysNo=(EditText) findViewById(R.id.getSysNo);
        getDep=(EditText) findViewById(R.id.getDp);
        addbtn=(AppCompatButton) findViewById(R.id.addbtn);
        logoubtn=(AppCompatButton) findViewById(R.id.logoutbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=getName.getText().toString();
                String AdmNo=getAdmNo.getText().toString();
                String SysNo=getSysNo.getText().toString();
                String Dep=getDep.getText().toString();
                Toast.makeText(getApplicationContext(),Name+" "+AdmNo+" "+SysNo+" "+Dep,Toast.LENGTH_LONG).show();
            }
        });
        logoubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });

    }
}