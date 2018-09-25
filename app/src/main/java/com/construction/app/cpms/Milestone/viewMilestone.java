package com.construction.app.cpms.Milestone;

import android.app.Activity;
import android.content.Intent;
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

public class viewMilestone extends AppCompatActivity {

    EditText txtName, txtDescription,txtEmpId,txtDate,txtTask;
    Button btnSave,btnDelete;
    TextView result;
    RequestQueue requestQueue;

    String showUrl = "https://projectcpms99.000webhostapp.com/scripts/Theeban/viewmilestone.php";
    String updateUrl = "https://projectcpms99.000webhostapp.com/scripts/Theeban/updatemilestone.php";
    String deleteUrl = "https://projectcpms99.000webhostapp.com/scripts/Theeban/deleteMilestone.php";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_milestone);

        // Text Fields and Buttons
        txtName = (EditText) findViewById(R.id.txtName);
        txtDescription = (EditText) findViewById(R.id.txtDesc);
        txtEmpId = (EditText) findViewById(R.id.txtEmpId);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTask = (EditText) findViewById(R.id.txtTask);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        id = getIntent().getExtras().getString("id");
        System.out.println("\n"+id+"\n");

        fetchData();

        //Update
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    public void fetchData() {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                showUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        try {

                            JSONArray milestone = new JSONArray(response);

                            for (int i = 0; i < milestone.length(); i++) {
                                JSONObject mls = milestone.getJSONObject(i);

                                String id = mls.getString("id");
                                String name = mls.getString("Name");
                                String description = mls.getString("Description");
                                String task = mls.getString("Task");
                                String date = mls.getString("Date");
                                String empId = mls.getString("Employee Id");

                                txtName.setText(name);
                                txtDescription.setText(description);
                                txtTask.setText(task);
                                txtDate.setText(date);
                                txtEmpId.setText(empId);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
                System.out.print("Error: "+error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("id", id);

                return postParam;
            }

        };

        requestQueue.add(jsonObjRequest);

    }

    public void update() {

        final String name = txtName.getText().toString();
        final String description = txtDescription.getText().toString();
        final String task = txtTask.getText().toString();
        final String date = txtDate.getText().toString();
        final String empId = txtEmpId.getText().toString();

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                updateUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        fetchData();

                        Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
                System.out.print("Error: "+error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("name", name);
                postParam.put("desc", description);
                postParam.put("id", id);
                postParam.put("date", date);
                postParam.put("task", task);
                postParam.put("empId", empId);

                System.out.print(name + description + id + date + task + empId);

                return postParam;
            }

        };

        requestQueue.add(jsonObjRequest);
    }

    public void delete() {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        finish();

                        Toast.makeText(getBaseContext(),"Successfully Deleted",Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
                System.out.print("Error: "+error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("id", id);

                return postParam;
            }

        };

        requestQueue.add(jsonObjRequest);
    }
}
