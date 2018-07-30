package org.apachehttprequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by remipassmoilesel on 18/01/17.
 */
public class Launcher {

    public static void main(String[] args) throws IOException {
        String url = "http://www.google.com/search?q=httpClient";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", "Hey hey !");
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result);
    }

}
