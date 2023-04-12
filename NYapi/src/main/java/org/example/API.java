package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class API{

    static ArrayList<Article> articleList = new ArrayList<>();

    public static void API(){
        String apiUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=" + Main.category + "&api-key=p22MjI6kAWFmQ9iTJrYSnglLn42VsXEb";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder json = new StringBuilder();

            while ((output = br.readLine()) != null) {
                json.append(output);
            }

            conn.disconnect();

            Gson gson = new Gson();
            Article article = gson.fromJson(json.toString(), Article.class);

            // Use myObj for further processing
            for(int i=0; i< article.response.docs.length; i++) {
                System.out.println("Article Title: " + article.response.docs[i].headline.main);
                System.out.println("Author: " + article.response.docs[i].byline.original);
                System.out.println("Article Date: " + article.response.docs[i].pub_date);
                System.out.println("Category: " + article.response.docs[i].section_name);
                System.out.println("Content: " + article.response.docs[i].lead_paragraph);
                for(int j =-9; j<article.response.docs[i].lead_paragraph.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }
            articleList.add(article);
            JDBC.articaleTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

