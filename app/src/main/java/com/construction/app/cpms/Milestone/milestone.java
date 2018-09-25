package com.construction.app.cpms.Milestone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.construction.app.cpms.R;
import com.construction.app.cpms.miscellaneous.ForumRecyclerViewAdapter;
import com.construction.app.cpms.miscellaneous.bean.ForumPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class milestone extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView lst;
    String [] milestones={

    };


    public Button but3, but2, but7;
    private static String json_string;

    private static RequestQueue requestQueue;
    private static String PHP_SCRIPT_URL = "https://projectcpms99.000webhostapp.com/scripts/Theeban/getmilestone.php";
    private static StringRequest stringRequest;

    private static ArrayList<MilestoneView> milestoneArrayList;  // Forum class is a bean.
    private static ForumRecyclerViewAdapter forumRecyclerViewAdapter;

    public void init(){
        but3= (Button)findViewById(R.id.but3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy =  new Intent(milestone.this,createMilestone.class);

                startActivity(toy);
            }
        });

        but7 = (Button)findViewById(R.id.but7);
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(milestone.this,DisplayListView.class);
                intent.putExtra("json_data",json_string);

                startActivity(intent);
            }
        });

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
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i<jsonArray.length(); i++){ //loop through jsonarray(stores objects in each index) and put data to arraylist.
                                System.out.println("FOR LOOP");
                                JSONObject object = jsonArray.getJSONObject(i);     //get the JSON object at index i

                                MilestoneView mls = new MilestoneView(object.getString("id"), object.getString("Name"),
                                        object.getString("Description"), object.getString("Task"), object.getString("Employee Id"), object.getString("Date"));
                                System.out.println(object.getString("Name"));
                                //populate arraylist
                                milestoneArrayList.add(mls);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);

//        lst= (ListView) findViewById(R.id.listvw);
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        lst.setAdapter(arrayAdapter);
//        lst.setOnItemClickListener(this);

        init();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        //fetchdata();
    }

    @Override
    public void onStart() {
        super.onStart();
        milestoneArrayList = new ArrayList();
        milestoneArrayList.clear();
        fetchdata();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv=(TextView)view;
        Toast.makeText(this, "You click on"+tv.getText()+position, Toast.LENGTH_SHORT).show();
    }

    public void parseJSON(View view){

    if (json_string==null){

        Toast.makeText(getApplicationContext(),"First GetJSON",Toast.LENGTH_LONG).show();

    }
    else
    {
        Intent intent = new Intent(this,DisplayListView.class);
        intent.putExtra("json_data",json_string);
        startActivity(intent);
    }
    }

}
