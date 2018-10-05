package com.construction.app.cpms.Milestone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.construction.app.cpms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class timeline extends AppCompatActivity {
    JSONObject jsonObject;
    private static JSONArray jsonArray;
    private static TimelineAdapter timelineAdapter;
    static ListView listView;

    private static String json_string;

    private static RequestQueue requestQueue;
    private static String PHP_SCRIPT_URL = "https://projectcpms99.000webhostapp.com/scripts/Theeban/getmilestone.php";
    private static StringRequest stringRequest;
    private static ArrayList<MilestoneView> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        initListView();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        fetchdata();

//        json_string = getIntent().getExtras().getString("json_data");
        System.out.println("\n"+json_string+"\n");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MilestoneView o = (MilestoneView) parent.getItemAtPosition(position);

                Intent intent = new Intent(timeline.this,viewMilestone.class);
                intent.putExtra("id",o.getId());

                startActivity(intent);
            }
        });
    }

    private void initListView() {
        listView = (ListView)findViewById(R.id.listView);

        LayoutParams params = listView.getLayoutParams();
        listView.setLayoutParams(params);

        timelineAdapter = new TimelineAdapter(this, R.layout.activity_time_adapter);
    }

    private static void fetchdata(){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                System.out.println("Do background func");
                stringRequest = new StringRequest(Request.Method.POST, PHP_SCRIPT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("HERE");
                        System.out.println(response.toString());
                        json_string = response.toString();

                        try {

                            jsonArray = new JSONArray(json_string);
                            System.out.println(jsonArray);
//            jsonObject = new JSONObject(json_string);
//            jsonArray = jsonObject.getJSONArray("");
                            int count = 0;
                            String id, name,description,task,employeeid,date;

                            while (count<jsonArray.length())
                            {
                                JSONObject JO = jsonArray.getJSONObject(count);

                                id = JO.getString("id");
                                name = JO.getString("Name");
                                description = JO.getString("Description");
                                task = JO.getString("Task");
                                employeeid = JO.getString("Employee Id");
                                date = JO.getString("Date");

                                MilestoneView milestoneView = new MilestoneView(id, name, description, task, employeeid, date);
                                listItems.add(milestoneView);
//                                timelineAdapter.add(milestoneView);
                                count ++;
                            }

                            Collections.sort(listItems,new DateComparator());

                            for (MilestoneView O : listItems) {
                                timelineAdapter.add(O);
                            }

                            listView.setAdapter(timelineAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }){

                };
                requestQueue.add(stringRequest);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };

        asyncTask.execute();
    }

    @Override
    public void onRestart()
    {  // After a pause OR at startup
        super.onRestart();
        initListView();
        //Refresh your stuff here
        fetchdata();
    }
}
