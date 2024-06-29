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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogEntry extends AppCompatActivity {
    EditText getName,getAdmNo,getSysNo,getDep;
    AppCompatButton addbtn,logoubtn;
    String apiurl="http://10.0.4.16:3000/api/students";

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
                //Reading values
                String Name=getName.getText().toString();
                String AdmNo=getAdmNo.getText().toString();
                String SysNo=getSysNo.getText().toString();
                String Dep=getDep.getText().toString();

                //Creating JSON object
                JSONObject student=new JSONObject();
                try {
                    student.put("name",Name);
                    student.put("admission_number",AdmNo);
                    student.put("system_number",SysNo);
                    student.put("department",Dep);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //JSON object request creation
                JsonObjectRequest JsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,//METHOD parameter
                        apiurl,//LINK parameter
                        student,//DATA parameter
                        //Response listener
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_LONG).show();
                            }
                        },
                        //Error listener
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(JsonObjectRequest);
            }
        });
        logoubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferance=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor= preferance.edit();
                editor.clear();
                editor.apply();
                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });

    }
}