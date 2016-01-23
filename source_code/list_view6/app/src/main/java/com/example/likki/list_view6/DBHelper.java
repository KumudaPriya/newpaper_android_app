package com.example.likki.list_view6;

/**
 * Created by likki on 11/25/15.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by anusha on 12/9/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NEWSPAPER.db";
    public static final String Newspaper_TABLE_NAME = "links";
    public static final String links_column_1 = "news_paper_name";
    public static final String links_column_2 = "category_name";
    public static final String links_column_3 = "article_url";
    public static final String links_column_4 = "title";
    public static final String links_column_5 = "image_url";

    public static final String links_column_6 = "paragraph";
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table links " +
                        "(news_paper_name text,category_name  text,article_url text,title text,image_url text,paragraph text not null);"
        );


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS links");
        onCreate(db);
    }
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("links", null, null);
    }

    public boolean insertLogin  (String news_paper_name, String category_name,String article_url,String title,String image_url,String paragraph)
    {
        System.out.println("kumudaaaaaaaaaaaaaaaaaaaa" + paragraph);
        String po="helloo";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("news_paper_name", news_paper_name);
        contentValues.put("category_name", category_name);
        contentValues.put("article_url", article_url);
        contentValues.put("title", title);
        contentValues.put("image_url", image_url);
        contentValues.put("paragraph", paragraph);
        try {
            contentValues.put("paragraph", paragraph);
        }catch (Exception e) {
            System.out.println("show error");
        }
        //contentValues.put("po", po);

        db.insert("links", null, contentValues);
        return true;
    }

    public String[][] getAllCotacts()
    {
        System.out.println("in get all contacts");
        String[][] array=new String[700][20];
        int i=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from links", null );
        if (res.moveToFirst()) {
            do {
                System.out.println("entereeeeeeeeeeeeeeeeed");
                array[i][0]=res.getString(0);
                array[i][4]=res.getString(1);
                array[i][2]=res.getString(2);
                array[i][6]=res.getString(3);
                array[i][8]=res.getString(4);
               array[i][10]=res.getString(res.getColumnIndex(links_column_6));
               // array[i][12]=res.getString();
                System.out.println("iaaaaaaage_url : "+array[i][8]);
                System.out.println("paagra"+array[i][10]);
                i++;

                System.out.println(i);
            } while (res.moveToNext());
        }

        return array;
    }

}
