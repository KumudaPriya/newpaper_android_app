package com.example.likki.list_view6;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by likki on 11/16/15.
 */
public class entertainment extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_paper);

    }
    public void displayhindu(View view) {
        MainActivity.news_string = "thehindu";
        MainActivity.cat_string = "entertainment";
        startActivity(new Intent(entertainment.this, MyListActivity.class));
        //goToUrl("http://www.thehindu.com/entertainment/");
    }

    public void displaydeccan(View view) {
        MainActivity.news_string = "deccanchronicle";
        MainActivity.cat_string = "entertainment";
        startActivity(new Intent(entertainment.this, MyListActivity.class));
        //goToUrl("http://www.deccanchronicle.com/entertainment");
    }

    public void displayreuters(View view) {
        MainActivity.news_string = "reuters";
        MainActivity.cat_string = "entertainment";
        startActivity(new Intent(entertainment.this, MyListActivity.class));
       // goToUrl ( "http://in.reuters.com/news/entertainment");
    }

    public void displaynytimes(View view) {
        MainActivity.news_string = "nytimes";
        MainActivity.cat_string = " ";
        goToUrl ( "http://www.nytimes.com/");
    }




    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
