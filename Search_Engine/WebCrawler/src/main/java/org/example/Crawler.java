package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    HashSet<String> urlset;
    int Max_Depth = 2;

    Crawler(){
        urlset = new HashSet<String>();
    }

    public   void getPageTextsAndLink(String url, int depth){

        if(urlset.contains(url)){  // if we got the url in that url set,, then return
            return;
        }

        if(depth >= Max_Depth){  // if depth exceeds the max depth limit ,, then return
            return;
        }

        if(urlset.add(url)){    // adding the url to urlSet to avoid adding duplicate url
            System.out.println(url);
        }

        depth++;

        try {
            /*This Jsoup class will convert(parse) the html objects to java objects
             * and we have taken the timeout of 5000 milliseconds ,, that if it is not able to find the url
             * in that time ,, we will move to next url */
            Document document = Jsoup.connect(url).timeout(500000).get();

            /*Indexer work starts from here*/
            Indexer indexer = new Indexer(document,url);
            System.out.println(document.title());


            // we are selecting all the hyper link from document and saving it to element object
            Elements availableLinksOnPage = document.select("a[href]");
            // recursive call
            for (Element currentLink : availableLinksOnPage) {
                /*we are getting the href values as a string object,, becoz curretLink is of Element object not of
                 * string ,, so to convert it to string we used attribute(attr) key and selected absolute value of href*/
                getPageTextsAndLink(currentLink.attr("abs:href"), depth);
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextsAndLink("https://www.javatpoint.com",0);
//        crawler.getPageTextsAndLink("https://www.pepcoding.com/",1);
    }
}