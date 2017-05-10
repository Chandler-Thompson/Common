package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebManipulation {
	
	public static String getRawHTML(String input) throws Exception {
		//System.out.println(input);
        URL url = new URL(input);
        
        //Create connection and set HTTP headers
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("Host", "google.com");
        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.2 ; Trident/4.0)");
        urlConnection.addRequestProperty("Accept", "text/html;q=0.9,*/*;q=0.8");
        urlConnection.addRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //urlConnection.addRequestProperty("Accept-Encoding", "gzip, deflate"); //Outputs weird characters if left in
        urlConnection.addRequestProperty("Referer", "http://www.google.com");
        urlConnection.addRequestProperty("Connection", "keep-alive");

        //Read page into String
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder rawHTML_sb = new StringBuilder();
        while ((inputLine = inputStream.readLine()) != null) {
        	rawHTML_sb.append(inputLine);
        }
        inputStream.close();
        
        //Format and return output
        String rawHTML = rawHTML_sb.toString();
        return rawHTML;
    }
	

}
