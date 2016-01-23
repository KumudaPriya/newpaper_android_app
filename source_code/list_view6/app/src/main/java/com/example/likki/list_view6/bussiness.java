package com.example.likki.list_view6;

/**
 * Created by likki on 11/16/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



/**
 * Created by Azure on 21-09-2015.
 */
public class bussiness extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_paper);

    }
    public void displayhindu(View view) {
        MainActivity.news_string = "thehindu";
        MainActivity.cat_string = "business";
        //goToUrl("http://www.thehindu.com/business/");
        System.out.println("enterd1");

        startActivity(new Intent(bussiness.this, MyListActivity.class));
    }

    public void displaydeccan(View view) {
        MainActivity.news_string = "deccanchronicle";
        MainActivity.cat_string = "business";
        startActivity(new Intent(bussiness.this, MyListActivity.class));
       // goToUrl("http://www.deccanchronicle.com/business");
    }

    public void displayreuters(View view) {
        MainActivity.news_string = "reuters";
        MainActivity.cat_string = "business";
        startActivity(new Intent(bussiness.this, MyListActivity.class));
        //goToUrl ( "http://in.reuters.com/finance");
    }

    public void displaynytimes(View view) {
        MainActivity.news_string = "nytimes";
        MainActivity.cat_string = "business";
        //goToUrl ( "http://www.nytimes.com/pages/business/index.html?action=click&pgtype=Homepage&region=TopBar&module=HPMiniNav&contentCollection=Business&WT.nav=page");
        startActivity(new Intent(bussiness.this, MyListActivity.class));
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}