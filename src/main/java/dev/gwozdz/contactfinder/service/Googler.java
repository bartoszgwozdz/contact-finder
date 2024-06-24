package dev.gwozdz.contactfinder.service;

import com.google.gson.Gson;
import dev.gwozdz.contactfinder.model.google.SearchEngineResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


@Component
public class Googler{
    private final String GOOGLE_DEPRECATED_SEARCH_URL = "https://www.google.com/search";
    private final String GOOGLE_CUSTOM_SEARCH_URL = "https://customsearch.googleapis.com/customsearch/v1?q=";

    @Value("${google.engineId}")

    private String GOOGLE_CUSTOM_ENGINE_ID;
    @Value("${google.apiKey}")
    private String apiKey; // Replace with your Google API key

    public SearchEngineResult googleCustomSearch(String query, int numberOfResponses) {
        String output = "";
        try {
            query = UriUtils.encodePath(query, "UTF-8");
            URI uri = new URI( GOOGLE_CUSTOM_SEARCH_URL + query + "&key=" + apiKey +"&cx=" + GOOGLE_CUSTOM_ENGINE_ID +  "&num=" + numberOfResponses);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                SearchEngineResult result = new Gson().fromJson(reader, SearchEngineResult.class);
                return result;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // It doesn't work, google asks for cookies and restrictions.
    // After all google doesn't allow to scrap google web search page
    @Deprecated
    public void googleStandardSearch(String query)
            throws IOException {
        String searchURL = GOOGLE_DEPRECATED_SEARCH_URL + "?q=" + query + "&num=" + 3;
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //below will print HTML data, save it to a file and open in browser to compare
//        System.out.println(doc.html());

        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
        }
    }
}
