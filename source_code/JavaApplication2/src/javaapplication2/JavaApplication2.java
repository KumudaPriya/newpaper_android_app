package javaapplication2;

import java.io.File;
import java.io.*;
import java.util.logging.*;
import static javaapplication2.ImageDownloader.saveImage;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.Jsoup;
import java.util.*;  
import org.jsoup.helper.Validate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JavaApplication2 {
   
  String str="";  
  int j;
  static String[][] article = new String[8000][8];
  
    
  public static void text(String url ,  int j) {
    try {
      
        Document doc = Jsoup.connect(url).timeout(10*1000).get(); 
      
       String title = "";
       if(article[j][0] != "nytimes" && article[j][0] != "reuters")
       title = TitleExtractor.getPageTitle(url);
       else if(article[j][0] == "nytimes"){
           StringTokenizer st = new StringTokenizer(article[j][1],"/.");
					String[] array = new String[st.countTokens()];
					
					int i = 0;
					
					while (st.hasMoreTokens() )  {
				    	 	array[i] = st.nextToken();												//read all tokens to an array
				    	 	i = i+1;
				    	 }	
                                        
                                        int k = 0;
                                        for(k=0;k<i;k++) {
                                            if(array[k].contains("html")) 
                                                title = array[k-1];
                                           
                                        }
                                        
                                         StringTokenizer st2 = new StringTokenizer(title,"-");
                                        String[] array2 = new String[st2.countTokens()];
                                        i = 0;
					String dummy = "";
					while (st2.hasMoreTokens() )  {
				    	 	array2[i] = st2.nextToken();												//read all tokens to an array
				    	 	
                                                i = i+1;
				    	 }
                                        
                                        for(k=0;k<i;k++) {
                                            dummy = dummy + array2[k] + " ";
                                        }
                                        title = dummy;
       }
      else if(article[j][0] == "reuters"){
          Elements h1tag = doc.select("h1");
           for (Element h1 : h1tag) {
               title = h1tag.text();
           }
      }
        article[j][3] = title;
    //  System.out.println(title);
        String paragraph = "";
       if(article[j][0] != "nytimes" && article[j][0] != "deccanchronicle"){ 
           Elements paragraphs = doc.select("p");
           for(Element p : paragraphs) {
               paragraph = paragraph + p.text();
               break;
             //System.out.println(p.text());
             //System.out.println("\n\n\n\n"); 
           } 
       }
       else if(article[j][0] == "nytimes"){
           Elements paragraphs = doc.select("p");
           for(Element p : paragraphs) {
               if(p.id().equalsIgnoreCase("story-continues-1")){
               paragraph = paragraph + p.text();
               break;
               }
             //System.out.println(p.text());
             //System.out.println("\n\n\n\n"); 
           } 
       }
       else if(article[j][0] == "deccanchronicle"){
           int m = 0;
           Elements division = doc.select("div");
           for(Element d : division) {
               if(d.className().equals("field-item even") ) {
                   Elements paragraphs = d.select("p");
                      for(Element p : paragraphs) {
                        //System.out.println("entered");
                        paragraph = paragraph + p.text();
                       // System.out.println(paragraph + "bb");
                        break;
                        
            // System.out.println(p.text());
            // System.out.println("\n\n\n\n"); 
                    }
                      
               }
             
           }
         
      }
     // System.out.println(paragraph + "\n"); 
      article[j][7] = paragraph;
     //  article[j][3] =  article[j][3] + "\n" + article[j][7];
      String main_img_url = "",img_width = "",img_height="",align = "";
      int max = 0,breaked = 0;
      
      if(article[j][0] == "reuters")   {
               // System.out.println(src.attr("abs:src"));
               //  System.out.println("enterd2");
                Elements division = doc.select("div");
           for(Element d : division) {
               if(d.className().equals("related-photo-container")) {
                   Elements images = d.select("img");
                  
                      for(Element p : images) {
                         breaked = 1;
                         System.out.println("entered ");
                        
                       
                if(   p.attr("abs:src").contains("reutersmedia.net")  )    {
                   System.out.println("entered main");
                   
                    
                 main_img_url = p.attr("abs:src");
                 System.out.println(main_img_url);
                        img_width = p.attr("width");
                        img_height = p.attr("height");
                        break;
                }
               }
               }
           }
      }
      
     Elements media = doc.select("[src]");
           for (Element src : media) {
               //System.out.println("enterd1");
            if ( article[j][0] == "thehindu" &&  src.tagName().equals("img")  )
		{
		 
                  if(  src.attr("abs:src").toLowerCase().contains("jpg")    ) {
                     
                    if( src.attr("width")=="" && src.attr("height") == "" ){
                       
                    }
                    else  if( (src.attr("width")=="" || src.attr("height") == "") && !(src.attr("width")==" " && src.attr("height") == " ") ) {
                        breaked = 1;
                       // System.out.println("space *  ");
                        main_img_url = src.attr("abs:src");
                        img_width = src.attr("width");
                        img_height = src.attr("height");
                        break;
                    }
                    else if(src.attr("width")==" " && src.attr("height") == " ");
                    else {
                        String str1 = src.attr("width").replaceAll("\\D+","");
                        String str2 = src.attr("height").replaceAll("\\D+","");
                        //System.out.println( str1);
                        //System.out.println( str2 );
                        max = Integer.parseInt( str1 )*Integer.parseInt( str2 );
                        main_img_url = src.attr("abs:src");
                       // System.out.println( main_img_url);
                        
                        img_width = src.attr("width");
                        img_height = src.attr("height");
                      //  System.out.println( img_width + "*" + img_height);
                    }
		}
                  
                }
            else if(article[j][0] == "deccanchronicle" &&  src.tagName().equals("img"))   {
                
                if(  src.attr("abs:src").toLowerCase().contains("jpg")   &&  src.attr("abs:src").contains("article_node_view")  )    {
                   
                    breaked = 1;
                main_img_url = src.attr("abs:src");
                        img_width = src.attr("width");
                        img_height = src.attr("height");
                        break;
                }
                else{
                    main_img_url = "http://dd508hmafkqws.cloudfront.net/sites/all/themes/deccan_chronicle/logo.png";
                };
            
            } 
            else if(article[j][0] == "nytimes" &&  src.tagName().equals("img"))   {
               // System.out.println(src.attr("abs:src"));
               //  System.out.println("enterd2");
                breaked = 1;
                if(  src.attr("abs:src").toLowerCase().contains("jpg")   &&  src.attr("abs:src").contains("nyt.com")  )    {
                   
                    
                 main_img_url = src.attr("abs:src");
                        img_width = src.attr("width");
                        img_height = src.attr("height");
                        break;
                }
            }
          /*  else if(article[j][0] == "reuters" &&  src.tagName().equals("img"))   {
               // System.out.println(src.attr("abs:src"));
               //  System.out.println("enterd2");
                Elements division = doc.select("div");
           for(Element d : division) {
               if(d.className().equals("related-photo-container") ) {
                   Elements images = d.select("[src]");
                  
                      for(Element p : images) {
                         breaked = 1;
                         System.out.println("entered ");
                if(   p.attr("abs:src").contains("reutersmedia.net")  )    {
                   System.out.println("entered main");
                   
                    
                 main_img_url = src.attr("abs:src");
                 System.out.println(main_img_url);
                        img_width = src.attr("width");
                        img_height = src.attr("height");
                        break;
                } 
                        
            // System.out.println(p.text());
            // System.out.println("\n\n\n\n"); 
                    }
                      
               }
             
           }
            }  */  
            
            } 
           
       if(breaked == 0) {    
       Elements media1 = doc.select("[src]");
           for (Element src : media1) {
            if (src.tagName().equals("img")    )
		{
		  
                  if(  src.attr("abs:src").contains("jpg")    ) {
                    if( !(src.attr("width")=="" && src.attr("height") == "") ) {
                        String str1 = src.attr("width").replaceAll("\\D+","");
                        String str2 = src.attr("height").replaceAll("\\D+","");
                    if(max < Integer.parseInt( str1 )*Integer.parseInt( str2 )) {
                            max = Integer.parseInt( str1 )*Integer.parseInt( str2 );
                            main_img_url = src.attr("abs:src");
                            img_width = src.attr("width");
                            img_height = src.attr("height");
                            align = src.attr("class");
                    }
                    } 
		}
                }
            
            } 
       }
       
       if(article[j][0] == "thehindu" && align.equals("bordered-image-ss")) {
           main_img_url = "http://www.thehindu.com/template/1-0-1/gfx/logo.jpg";
                            img_width = "";
                            img_height = "";
       }
       else if(article[j][0] == "reuters" && main_img_url.equals("") ){
                 //   System.out.println("enterd");
                    main_img_url = "http://1hdlxe1oe0ll1eghud24le5v.wpengine.netdna-cdn.com/wp-content/uploads/2014/11/Reuters-Logo1.jpg";
                }; 
        if(article[j][0] == "nytimes" && main_img_url.equals("") ){
                 //   System.out.println("enterd");
                    main_img_url = "http://logodatabases.com/the-new-york-times-logo.html/the-new-york-times-logo-2";
                };
                
             
       
      
        
          /* print_img("main image - %s",
                             main_img_url
                             );

                     print_img("  %sx%s ",
                                   img_width, img_height);*/
                 //    System.out.println(align);
           
            article[j][4] = main_img_url;
            article[j][5] = img_width;
            article[j][6] = img_height;
     
           
      
      //String title = TitleExtractor.getPageTitle(url);
      //System.out.println(title + ":");
      //System.out.println("\n\n\n\n");
      
      /*String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
		String destinationFile = "image.jpg";

		saveImage(imageUrl, destinationFile);*/
       
      
    } 
    catch (IOException ex) {
      Logger.getLogger(JavaApplication2.class.getName())
            .log(Level.SEVERE, null, ex);
    }
    
    
}  
  public static void initiailize(String[][] article) {
      
      article[0][0] = "";
      article[0][1] = "";
      article[0][2] = "";
      article[0][3] = "";
      article[0][4] = "";
      article[0][5] =  "";  
      article[0][6] =  "";  
      article[0][7] =  "";  
  } 
          
  public static void main(String[] args)  {
    
    
    try {
        
        JavaApplication2 ls = new JavaApplication2();
        
       ex1();
        
        
        int i,j;
		
                 
                String[] link_url = new String[15];
                link_url[0] = "http://www.thehindu.com/business/";
                link_url[1] = "http://www.thehindu.com/sport/";
                link_url[2] = "http://www.thehindu.com/sci-tech/"  ;  
                link_url[3] =  "http://www.thehindu.com/entertainment/";     
                link_url[4] = "http://www.deccanchronicle.com/business";
                link_url[5] = "http://www.deccanchronicle.com/sports";
                link_url[6] = "http://www.deccanchronicle.com/technology";
                link_url[7] = "http://www.deccanchronicle.com/entertainment";
                link_url[8] = "http://in.reuters.com/finance";  
                link_url[9] = "http://in.reuters.com/news/sports";
                 link_url[10] = "http://in.reuters.com/news/technology";
                 link_url[11] = "http://in.reuters.com/news/entertainment";
                link_url[12] = "http://www.nytimes.com/pages/business/index.html?action=click&pgtype=Homepage&region=TopBar&module=HPMiniNav&contentCollection=Business&WT.nav=page";
                link_url[13] = "http://www.nytimes.com/pages/sports/index.html";
                link_url[14] = "http://www.nytimes.com/pages/technology/index.html?action=click&pgtype=Homepage&region=TopBar&module=HPMiniNav&contentCollection=Tech&WT.nav=page";
               
                
                ArrayList<String[][]> list =new ArrayList<String[][]>();   
        
                
                for(i=0;i<8000;i++)
                 initiailize(article);
                 j = 0;
        
        for(i = 0 ; i <=14 ; i++) {
            String main_link = link_url[i];
        String url =  link_url[i];
      //  String url = some[i][2];
       

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
       
        

       

        
        for (Element link : links) {
            
            //String[][] article = new String[1][8];
               // initiailize(article);
            
            article[j][1] = link.attr("abs:href");
            if( link.attr("abs:href").contains("www.thehindu.com")  ) {
                article[j][0] = "thehindu";
                if( link.attr("abs:href").contains("business") ) {
                    article[j][2] = "business";
                }
                else if( link.attr("abs:href").contains("sport") ){
                    article[j][2] = "sport";
                }
                else if( link.attr("abs:href").contains("sci-tech") ) {
                    article[j][2] = "sci-tech";
                }
                else if( link.attr("abs:href").contains("entertainment") ) {
                    article[j][2] = "entertainment";
                }
                else {
                    article[j][2] = "";
                }
            }
            else if( link.attr("abs:href").contains("www.deccanchronicle.com") ) {
                article[j][0] = "deccanchronicle";
                if( link.attr("abs:href").contains("business") ) {
                    article[j][2] = "business";
                   
                }
                else if( link.attr("abs:href").contains("sports") ){
                    article[j][2] = "sports";
                }
                else if( link.attr("abs:href").contains("technology") ) {
                    article[j][2] = "technology";
                }
                else if( link.attr("abs:href").contains("entertainment") ) {
                    article[j][2] = "entertainment";
                }
                else {
                    article[j][2] = "";
                }
            }
            else if( link.attr("abs:href").contains("http://in.reuters.com") ) {
               // System.out.println("entr reutres");
                article[j][0] = "reuters";
                
                if( main_link.contains("finance") ){
                    article[j][2] = "business";
                }
                else if( main_link.contains("sports") ) {
                    article[j][2] = "sports";
                }
                else if( main_link.contains("technology") ) {
                    article[j][2] = "technology";
                }
                else if( main_link.contains("entertainment") ) {
                    article[j][2] = "entertainment";
                }
                
                else {
                    article[j][2] = "";
                }
            }
            else if( link.attr("abs:href").contains("www.deccanherald.com") ) {
                
                article[j][0] = "deccanherald";
                if( link.attr("abs:href").contains("business") ) {
                    article[j][2] = "business";
                }
                else if( link.attr("abs:href").contains("sports") ){
                    article[j][2] = "sports";
                }
                
                else if( link.attr("abs:href").contains("entertainment") ) {
                    article[j][2] = "entertainment";
                }
                else {
                    article[j][2] = "";
                }
            }
            else if( link.attr("abs:href").contains("http://www.nytimes.com/") ) {
                
                article[j][0] = "nytimes";
                 if( link.attr("abs:href").contains("technology") ) {
                    article[j][2] = "technology";
                }
               else if( link.attr("abs:href").contains("business") ) {
                    article[j][2] = "business";
                }
               else if( link.attr("abs:href").contains("sports") ){
                    article[j][2] = "sports";
                }
                
               
                else {
                    article[j][2] = "";
                }
            }
            
            
                    
            else {
                 article[j][0] = "";
                 article[j][2] = "";
            }
           // ls.print(article[0][2] ,"%s", link.attr("abs:href"));
            if(article[j][2] != "" && article[j][0] == "reuters") {
                article[j][1] = ls.print("" ,"article","%s", link.attr("abs:href"));
                //System.out.println("starting");
                // System.out.println(article[j][1]);
            }
              else  if(article[j][2] != "" && article[j][0] != "deccanherald"&& article[j][0] != "nytimes") 
              article[j][1] = ls.print(article[j][2] ,"article","%s", link.attr("abs:href"));
                
                else if(article[j][2] != "" && article[j][0] == "nytimes")
                    article[j][1] = ls.print(article[j][2] ,"ref=","%s", link.attr("abs:href"));
                else;
                
              if(article[j][2] != "" && article[j][1] != "" ) {
               //System.out.println("starting2");
//                text(article[j][1] , article );
                  
                  System.out.println(article[j][1]);
                  text(article[j][1] ,j );
               // System.out.println( j + " " +article[j][3]);
                if(  article[j][7] != null&& article[j][7] != "" && article[j][7].lastIndexOf("") != 0 && article[j][3] != null && article[j][3] != "" && article[j][3].lastIndexOf("") != 0 ){
                //    System.out.println( article[j][3].lastIndexOf("") );
                   // System.out.println( "title : " + article[j][3] );
                   // System.out.println( article[j][4]);
                    Pattern p = Pattern.compile(".*[a-zA-Z].*");
          //Pattern p = Pattern.compile("[a-zA-Z]");  
                       Matcher m = p.matcher(article[j][7]);  
                      if( m.find() ) 
                        j++;
                }
                else ;
                //list.add(article);
              }
              else;
              
               

              
        }
        System.out.println( j);
        
        
        }
        /*System.out.println("starting");
        String[][] two_d_article = new String[list.size()][8];
        System.out.println("starting");
       two_d_article = list.toArray(two_d_article);*/
       // String[] stockArr = new String[stockList.size()];
        for(i=0;i<j;i++) {
            //System.out.println("starting");
            System.out.println(article[i][1]);
            //System.out.println(article[i][3]);
            System.out.println(article[i][4]);
            //System.out.println(article[i][7] + "\n");
            //System.out.println("starting");
           ex(i);
              
        
        }
    }
    
    catch (IOException ex) {
      Logger.getLogger(JavaApplication2.class.getName())
            .log(Level.SEVERE, null, ex);
    }
      
       
  }
  
  String print(String cat ,String matcher , String msg, Object... args) {
        String s=String.format(msg, args);
        if(s.contains(cat)&&s.contains(matcher)&&s.contains("#comments") == false && s.contains("widget-art=four-video") == false && s.contains("- Video | The Times of India:") == false)
       
           if( str.equals(s) == false)
           {
              //System.out.println(s);
                  String url = "";
                  url = url + s;
                 // System.out.println("starting1");
                 // System.out.println("starting");
                // text(url);
               str = s;
               return  s;
           }
           else {
              return "";
         }
        
        else {
          return "";
        }    
    }
  
  private static void  print_img(String msg, Object... args) {
        String s=String.format(msg, args);
        //if( s.contains("thehindu") ) {
         
         System.out.println(s);
        //}
  }

   String  trim(String s, int width) {
        if (s != null && s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
   
   public static void ex(int j) {
                    //function to get name and department name of student with given id as resultset
                    try {
                        Class.forName("org.postgresql.Driver");
			String dname = "my_postgres_db1";
                        String t = "";
                        String uname = "likki", pword = "sahana";
                        String p = "jdbc:postgresql://localhost:5432/"+dname + "?user=" + uname + "&password=" + pword;
                        Connection myConn = DriverManager.getConnection(p);
                        String insertTableSQL = "Insert into information (newspaper_name,article_url,category,title,image_url,width,height,paragraph) values(?,?,?,?,?,?,?,?) ";
                        PreparedStatement preparedStatement = myConn.prepareStatement(insertTableSQL);

                        //connection to database

                        try {
                            preparedStatement.setString(1,  article[j][0]);
                            preparedStatement.setString(2,  article[j][1]);
                            preparedStatement.setString(3,  article[j][2]);
                            preparedStatement.setString(4,  article[j][3]);
                            preparedStatement.setString(5,  article[j][4]);
                            preparedStatement.setString(6,  article[j][7]);
                            preparedStatement.setString(7,  article[j][6]);
                            preparedStatement.setString(8,  article[j][5]);
                            preparedStatement.executeUpdate();
                            System.out.println("tuple is inserted");
                            //executing query stored in selectSQL string and storing tuples in resultset
                        } catch (Exception sqle) {
                            //if sql exception occurs it enters catch
                            System.out.println("could not find any tuple " + sqle);
                        }
                 //   ResultSet rs1 = preparedStatement.executeQuery();
                    

                        myConn.close(); //closing the connection to dtabase
                        //return rs1;
                    }catch (Exception sqle) {
                        System.out.println("Exception: " + sqle);
                       // return null;
                    }
                    
                }
   
   public static void ex1() {
                    
                    try {
                        Class.forName("org.postgresql.Driver");

                        Class.forName("org.postgresql.Driver");
			String dname = "my_postgres_db1";
                        String t = "";
                        String uname = "likki", pword = "sahana";
                        String p = "jdbc:postgresql://localhost:5432/"+dname + "?user=" + uname + "&password=" + pword;
                        Connection myConn = DriverManager.getConnection(p);

                        String deleteSQL = "DELETE FROM information";
                        //connection to database
                        PreparedStatement preparedStatement = myConn.prepareStatement(deleteSQL);

                        
                        preparedStatement.executeUpdate();
                        //closing the connection to dtabase
                    } catch (Exception sqle) {
                        //if sql exception occurs it enters catch
                        System.out.println("Exception: " + sqle);
                    }
                   
                }
   
  
  
}