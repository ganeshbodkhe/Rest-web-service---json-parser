package com.example.apostle.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    PackAdapter packAdapter;
    RequestQueue requestQueue;

    List<Pack_list> pack_list = new ArrayList<>();
    RecyclerView recyclerList;

    public static final String chid = "ch_id";
    public static final String userid="user_id";

    private final String PACK_Url="here is your post url";

    String ch_id="1";
    String User_id="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPackOperation();

        recyclerList = (RecyclerView)findViewById(R.id.reclist);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setHasFixedSize(true);
        recyclerList.setLayoutManager(llm);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void GetPackOperation() {

        StringRequest stringrequest = new StringRequest(Request.Method.POST,PACK_Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("Response " + response.toString());

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String Pack_id = jsonObject.getString("pack_id");
                        String Pack_image = jsonObject.getString("pack_img");
                        String Pack_name = jsonObject.getString("pack_name");
                        String Pack_status = jsonObject.getString("pack_status");

                        Pack_list packlist = new Pack_list();

                        packlist.setPack_id(Pack_id);
                        packlist.setPack_image(Pack_image);
                        packlist.setPack_name(Pack_name);
                        packlist.setPack_status(Pack_status);

                        pack_list.add(packlist);

                    }

                    setPackAdapter();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }

                })
        {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put(chid,ch_id);
                params.put(userid,User_id);

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);

    }


    private void setPackAdapter() {

        if(pack_list!=null){

            packAdapter= new PackAdapter(this,pack_list);
            recyclerList.setAdapter(packAdapter);
            packAdapter.notifyDataSetChanged();
        }

    }

}
