package SearchEngine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;;

/**
 * Crawler class
 * @author Abdul Hammad, Mustafa, Swaroop Mensinkai
 *
 */
public class Crawler {

    private static ArrayList<String> linkList = new ArrayList<>();

    /**
     * Constructor
     * @param url
     */
    public Crawler(String url) {   
        getLinks(url);
        htmlToText();
    }

    /**
     * Method- getURLList
     * this method fetches the list of URLs and
     * @return list of string type of URLs
     */
    public String[] getURLList () {
        String[] urlList = linkList.toArray(new String[linkList.size()]);
        return urlList;
    }

    /**
     * Method- getLink
     * 
     * @param url
     */
    private static void getLinks(String url) {
        Document page;
        try {
            page = Jsoup.connect(url).get();
            Elements links = page.select("a[href]");
            for (Element link : links) {
                String absURL = link.attr("abs:href");
                String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
                Pattern urlPattern = Pattern.compile(regex);
                Matcher matchURL = urlPattern.matcher(absURL);
                while (matchURL.find()) {
                    linkList.add(matchURL.group(0));
                }
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Method- converter
     * 
     */
    private static void converter (String inpath, String outpath) throws IOException {   
        File file = new File(inpath);
        Document document = Jsoup.parse(file, "UTF-8");    
        String outputText = document.text(); 
        BufferedWriter writerText = new BufferedWriter(new FileWriter(outpath)); 
        writerText.write(outputText);
        writerText.close();
    }

    /**
     * method- htmlToText
     */
    private static void htmlToText() {
    	
        try {
            
        	for (String link : linkList) {
                
            	String regex = "[a-zA-Z0-9]+";
                Pattern linkPattern = Pattern.compile(regex);
                Matcher matchLink = linkPattern.matcher(link);
                StringBuffer stringBuffer = new StringBuffer();
               
                while (matchLink.find()) {
                	stringBuffer.append(matchLink.group(0));
                }

                String linkAdress = stringBuffer.substring(0);

                Document urlLink = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .referrer("http://www.google.com").ignoreHttpErrors(true).timeout(10*1000).get();
                String storeHTML = "dat/HTML files/";
                String html = urlLink.html();
                File htmlFolder = new File(storeHTML);
                
                if (!htmlFolder.exists() && !htmlFolder.isDirectory()) {
                    htmlFolder.mkdir();
                }  
                
                PrintWriter text = new PrintWriter(storeHTML + linkAdress + ".html");
                text.println(html);
                text.close();

                String storeText = "dat/Text Files/";
                File textFolder = new File(storeText);
               
                if (!textFolder.exists() && !textFolder.isDirectory()) {
                    textFolder.mkdir();
                }   
                
                File folder = new File(storeHTML);
                File[] fileStream = folder.listFiles();
                assert fileStream != null;
                
                for (File file : fileStream) {  
                   String htmlFile = storeHTML + file.getName();
                   String textFile = storeText + file.getName().replaceAll(".htm", "") + ".txt";
                   converter(htmlFile, textFile);
  
                }
            }
        } catch (Exception error) {
            
        	System.out.println("URL cannot be fetched:"+error);
        } 
    }

    /**
     * 
     * main method
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	
    	getLinks("https://www.wikipedia.org/");   	
    	getLinks("https://github.com/");
    	getLinks("https://www.hackerrank.com/");
    	getLinks("https://www.w3schools.com/");
    	getLinks("https://stackoverflow.com/");
    	getLinks("https://www.geeksforgeeks.org/");
		htmlToText();
    	
		}
}
