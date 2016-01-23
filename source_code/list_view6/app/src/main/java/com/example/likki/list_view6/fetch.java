package com.example.likki.list_view6;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by likki on 11/20/15.
 */
public class fetch extends Activity {

    public  void callMe (String url){

        System.out.println("enterd2");

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray arg0) {
                System.out.println("enterd3");
                // TODO Auto-generated method stub
                Log.i("Aditya", arg0.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);
    }


}
