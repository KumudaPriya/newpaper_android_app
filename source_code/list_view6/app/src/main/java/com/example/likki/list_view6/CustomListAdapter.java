package com.example.likki.list_view6;

/**
 * Created by likki on 11/16/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by likki on 11/16/15.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<MyListActivity.NewsItem> listData;
    private LayoutInflater layoutInflater;

    private final Context context;




    public CustomListAdapter(Context aContext, ArrayList<MyListActivity.NewsItem> listData) {
        System.out.println("enterd7");
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
        System.out.println("enterd7");
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("enterd8");
        ViewHolder holder;
        if (convertView == null) {
            System.out.println("enterd9");
            convertView = layoutInflater.inflate(R.layout.listrow, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.firstLine);
            holder.paragraphView = (TextView) convertView.findViewById(R.id.secondLine);
            holder.image_urlView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).gettitle() + " :");
        holder.titleView.setText(listData.get(position).gettitle());
        if(listData.get(position).getparagraph().length() > 70) {
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getparagraph().substring(0, 70) + "....");
            holder.paragraphView.setText(listData.get(position).getparagraph().substring(0, 70) + "....");
        }else {
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getparagraph() + "....");
            holder.paragraphView.setText(listData.get(position).getparagraph() + "....");
        }
        System.out.println("enterd10");



        Picasso.with(context)
                .load(listData.get(position).getimage_url())
                .placeholder(R.drawable.load) // optional
                .error(R.drawable.load)         // optional
                .into(holder.image_urlView);
        System.out.println("enterd11");
        //  holder.reportedDateView.setText(listData.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView paragraphView;
        ImageView image_urlView;
    }
}
