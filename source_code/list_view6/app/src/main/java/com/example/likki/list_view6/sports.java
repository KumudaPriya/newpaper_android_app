package com.example.likki.list_view6;

/**
 * Created by likki on 11/16/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class sports extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_paper);

    }
    public void displayhindu(View view) {
        MainActivity.news_string = "thehindu";
        MainActivity.cat_string = "sport";
        startActivity(new Intent(sports.this, MyListActivity.class));
       // goToUrl("http://www.thehindu.com/sport/");
    }

    public void displaydeccan(View view) {
        MainActivity.news_string = "deccanchronicle";
        MainActivity.cat_string = "sports";
        startActivity(new Intent(sports.this, MyListActivity.class));
       // goToUrl("http://www.deccanchronicle.com/sports");
    }

    public void displayreuters(View view)
    {   MainActivity.news_string = "reuters";
        MainActivity.cat_string = "sports";
        startActivity(new Intent(sports.this, MyListActivity.class));
        //goToUrl ( "http://in.reuters.com/news/sports");
    }

    public void displaynytimes(View view) {
        MainActivity.news_string = "nytimes";
        MainActivity.cat_string = "sports";
        startActivity(new Intent(sports.this, MyListActivity.class));
       // goToUrl ( "http://www.nytimes.com/pages/sports/index.html");
    }


    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
