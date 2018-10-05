package com.construction.app.cpms.Milestone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.construction.app.cpms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class createMilestone extends AppCompatActivity {

    EditText H_text1; //Name
    EditText H_text2; //Description
    EditText H_text3; //Task
    EditText H_text4; //EmployeeId
    EditText H_text5; //Date


    EditText name,description,emp_id,date,task;
    Button save;
    TextView result;
    RequestQueue requestQueue;
    String insertUrl = "http://projectcpms99.000webhostapp.com/scripts/Theeban/createmilestone.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_milestone);

        H_text1=(EditText)findViewById(R.id.H_text1);
        H_text2=(EditText)findViewById(R.id.H_text2);
        H_text3=(EditText)findViewById(R.id.H_text3);
        H_text4=(EditText)findViewById(R.id.H_text4);
        H_text5=(EditText)findViewById(R.id.H_text5);

        H_text1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(H_text1.getText().length()<1){
                    H_text1.setError("error");
                }
            }
        });

        H_text2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(H_text2.getText().length()<1){
                    H_text2.setError("error");
                }
            }
        });

        H_text3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(H_text3.getText().length()<1){
                    H_text3.setError("error");
                }
            }
        });

        H_text4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(H_text4.getText().length()<1){
                    H_text4.setError("error");
                }
            }
        });

        H_text5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(H_text5.getText().length()<1){
                    H_text5.setError("error");
                }
            }
        });


        name = (EditText) findViewById(R.id.H_text1);
        description = (EditText) findViewById(R.id.H_text2);
        emp_id = (EditText) findViewById(R.id.H_text4);
        date = (EditText) findViewById(R.id.H_text5);
        task = (EditText) findViewById(R.id.H_text3);
        save = (Button) findViewById(R.id.but1);
        result = (TextView) findViewById(R.id.textView);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(getApplicationContext(),"Successfully Created Milestone!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("name", name.getText().toString());
                        parameters.put("description",description.getText().toString());
                        parameters.put("emp_id", emp_id.getText().toString());
                        parameters.put("date", date.getText().toString());
                        parameters.put("task", task.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}
