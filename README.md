# newpaper_android_app

REQUIRED PLATFORMS : 
		INSTALL IDE like NETBEANS from ------>  https://netbeans.org/community/releases/80/install.html
		INSTALL python libraries web.py from ------> http://webpy.org/install
		INSTALL Android Studio from --------> http://developer.android.com/sdk/index.html?gclid=CMT1rpLlsskCFdUTaAodY74CMg

EXTERNAL JAR FILES :	
		DOWNLOAD jsoup-1.8.3.jar , jsoup-1.8.3-javadoc.jar , jsoup-1.8.3-sources.jar from --->  http://jsoup.org/download	
	 	Add above jar files in NETBEANS.
 
		DOWNLOAD picasso-2.5.2.jar file from ---> http://mvnrepository.com/artifact/com.squareup.picasso/picasso/2.5.2
		Add above jar files in Android Studio.
		

Procedure: 

Step 1 :	Extracting all articles from their respective websites and storing extracted information in database.
		
		Run javaAppliction2 (java project) in IDE like netbeans etc...

Step 2 :	Creating a Web server to post information from database to it.
	
		Compile code1.py ----> local web server is created.

Step 3 :	Creating android platform for the app.	

		Run android project list_view6 in android studio  	
	
		before running the project change the url to internal ip address of the pc or laptop 
		url ----> "http://your_ip_address:port"
		Run  "ifconfig" command in terminal to know the ip address.
