package com.rajesh.java.maven.rest;

import sun.net.www.content.text.PlainTextInputStream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rajesh on 25/03/2017.
 */
public class TestClient {

    public static void main(String[] args) {
        String uri =
                "http://localhost:8080/hello/rajesh";
        URL url = null;
        byte[] response = new byte[1000];
        String strResponse = "";
        try {
            url = new URL(uri);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");

        System.out.println("connection.getContent() = "+connection.getContent());
        System.out.println("connection.getResponseMessage() = "+connection.getResponseMessage());

        /*PlainTextInputStream stream = (PlainTextInputStream) connection.getContent();
        while (stream.read(response) > 0){
            strResponse = strResponse + response.toString();
        }*/

        strResponse = readString((InputStream) connection.getContent());

        System.out.println("strResponse = "+strResponse);
        connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readString(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String content = br.lines().reduce("", String::concat);
        return content;
    }
}
