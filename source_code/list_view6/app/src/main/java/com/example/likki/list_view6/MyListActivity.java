package com.example.likki.list_view6;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by likki on 11/16/15.
 */
public class MyListActivity extends ListActivity {

    private ArrayList<NewsItem> results_priv = new ArrayList<NewsItem>();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        System.out.println("enterd1");
        ArrayList image_details = getListData();
        System.out.println("enterd2");
        CustomListAdapter adapter = new CustomListAdapter(this, image_details);
        System.out.println("enterd3");
        setListAdapter(adapter);
        System.out.println("enterd4");

    }

    public class NewsItem {
        /*private String headline;
        private String reporterName;
        private String date;*/

        private String news_paper_name;
        private String category_name;
        private String article_url;
        private String title;
        private String image_url;
        private String width;
        private String height;
        private String paragraph;

        public String getnews_paper_name() {
            return news_paper_name;
        }

        public void setnews_paper_name(String news_paper_name) {
            this.news_paper_name = news_paper_name;
        }

        public String getcategory_name() {
            return category_name;
        }

        public void setcategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getarticle_url() {
            return article_url;
        }

        public void setarticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String title) {
            this.title = title;
        }

        public String getimage_url() {
            return image_url;
        }

        public void setimage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getwidth() {
            return width;
        }

        public void setwidth(String width){
            this.width = width;
        }

        public String getheight() {
            return height;
        }

        public void setheight(String height) {
            this.height = height;
        }

        public String getparagraph() {
            return paragraph;
        }

        public void setparagraph(String paragraph) {
            this.paragraph = paragraph;
        }



    }

    private ArrayList getListData() {


        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
       // NewsItem newsData =  new NewsItem();

        for(int i=0;i<MainActivity.count;i++) {
            NewsItem newsData =  new NewsItem();
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][0]);
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.news_string);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA" + MainActivity.cat_string);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA" + MainActivity.a[i][4]);
            if (MainActivity
            .a[i][0].equals(MainActivity.news_string) && MainActivity.a[i][4].equals(MainActivity.cat_string)) {
                //NewsItem newsData = new NewsItem();
                newsData.setnews_paper_name(MainActivity.a[i][0]);
                newsData.setcategory_name(MainActivity.a[i][4]);
                newsData.setarticle_url(MainActivity.a[i][2]);
                newsData.settitle(MainActivity.a[i][6]);
                newsData.setimage_url(MainActivity.a[i][8]);
                //newsData.setwidth(MainActivity.a[i][10]);
               // newsData.setheight(MainActivity.a[i][12]);
                newsData.setparagraph(MainActivity.a[i][10]);

                results.add(newsData);
              /*  System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][0]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.news_string);
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAA" + MainActivity.cat_string);
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAA" + MainActivity.a[i][1]); */
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][0]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][2]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][4]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][6]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][8]);
                System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + MainActivity.a[i][10]);
            }
        }
       // Collections.copy(results_priv,results);
        results_priv = (ArrayList<NewsItem>)results.clone();
        for(int j = 0; j < results_priv.size(); j++) {
       // System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results.size());
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).article_url);
        }
        //results_priv = results;
        // Add some more dummy data for testing
        return results;
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      /* String item = (String) getListAdapter().getItem(position);
        Intent data = new Intent();
        data.putExtra("Order",item);
        setResult(RESULT_OK, data);
        finish();
        Object o = results.get(position);
        NewsItem newsData = (NewsItem) o;

        Object o = results1.get(position);
        NewsItem newsData = (NewsItem) o;

        */
       // ArrayList<NewsItem> results1 = new ArrayList<NewsItem>();
       // results1 = getListData();

        NewsItem newsData = new NewsItem();
        newsData = results_priv.get(position);
        for(int i = 0; i < results_priv.size(); i++) {
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCC"  + results_priv.get(i).getarticle_url());
        }

        System.out.println("BBBBBBBBBBBBBBBBBBBBb" + newsData.getarticle_url());
        String url = "https://restcountries.eu/rest/v1/callingcode/91";

        /*fetch ls;
        ls  = new fetch();
        ls.callMe(url);*/
       // NewsItem item = (NewsItem) getListAdapter().getItem(position);
        goToUrl (newsData.getarticle_url());
    }

}
