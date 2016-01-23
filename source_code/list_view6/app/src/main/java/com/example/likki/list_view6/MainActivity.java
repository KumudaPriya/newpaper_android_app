package com.example.likki.list_view6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends Activity {



    public  static  String cat_string = " ";
    public  static  String news_string = " ";
    public static int count = 0;
    public static String[][] a = new String[700][20];
    DBHelper mydb = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread timer = new Thread(){
            public void run() {
                try{
                    // callMe("http://172.16.5.176:3000");
                    sleep(5000);
                }
                catch (InterruptedException e)

                {
                    e.printStackTrace();
                }

                finally {
                    callMe("http://172.16.5.34:8080");
                    startActivity(new Intent(MainActivity.this,category.class));
                }
            }
        };
        timer.start();
    }

    public  void callMe (String url){

        System.out.println("enterd2");


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONArray>() {

            //   String[][] array = new String[800][20];
            int j;

            @Override
            public void onResponse(JSONArray arg0) {
                System.out.println("entered");
                // TODO Auto-generated method stub
                Log.i("Aditya \\/", arg0.toString());



                ArrayList<String> list = new ArrayList<String>();
                //  JSONArray jsonArray = (JSONArray)jsonObject;
                if (arg0 != null) {
                    int len = arg0.length();
                    for (int i=0;i<len;i++){
                        try {
                            list.add(arg0.get(i).toString().replaceAll("\\\\",
                                    ""));
                            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD" +i +list.get(i));
                        }
                        catch (Exception e) {
                            System.out.println("errorrrrrrrrr");
                            String[][] aray ;
                            aray=mydb.getAllCotacts();
                          //  int l=aray.length();
                           // count = len;
                            a = aray;
                            for(int j=0;j<400;j++)
                            {

                                for(int k=0;k<=10;k=k+2)
                                {
                                    System.out.println("success     "+aray[j][k]);
                                }
                            }

                            System.out.println("cmpletedddddd");

                        }
                    }
                    count = len;
                    mydb.deleteAll();
                    for(int i=0;i<len;i++) {
                        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEE" +i);
                        StringTokenizer st = new StringTokenizer(list.get(i),"\"[]");
                        j = 0;
                        while (st.hasMoreTokens() )  {
                            a[i][j] = st.nextToken();
                            System.out.println( a[i][j] );
                            j = j+1;
                        }
                        System.out.print("anushaaaaaaaaaaaaaaaaaaaaaaaa"+a[i][10]);
                       mydb.insertLogin(a[i][0], a[i][4], a[i][2], a[i][6], a[i][8],a[i][10]);


                    }
                    System.out.println("heloooooohyuttyhghfghgfgfngngnghnhgnhgnfgghh\ndfgdfgfhfghhgh\nfhgfhhhy");
                   // int i= len(a);
                    String[][] aray ;
                    aray=mydb.getAllCotacts();
                    a = aray;
                    for(int j=0;j<400;j++)
                    {

                        for(int k=0;k<=12;k=k+2)
                        {
                            System.out.println("success     "+aray[j][k]);
                        }
                    }
                    System.out.println("cmpletedddddd");

                }




            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub

                System.out.println("error entered");
                String[][] aray ;
                aray=mydb.getAllCotacts();
                a = aray;
                for(int j=0;j<400;j++)
                {

                    for(int k=0;k<=10;k=k+2)
                    {
                        System.out.println("success     "+aray[j][k]);
                    }
                }
                System.out.println("cmpletedddddd");


            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);
    }





}


