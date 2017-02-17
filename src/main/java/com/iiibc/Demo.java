package com.iiibc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by gaob on 2/12/2017.
 */
public class Demo {

    static public void putItem(){

        String item = "{\"TableName\" : \"user\", "
                    + "\"Key\" : {\"userid\" : { \"S\" : \"chloe\"}},"
                    + "\"UpdateExpression\" : \"SET points =:attrValue\","
                    //+ "\"ExpressionAttributeNames\" : {\"#attrName\" : \"points\"},"
                    + "\"ExpressionAttributeValues\" : {\":attrValue\" : {\"N\" :\"12\"}}}";


       String item1 = "{\"TableName\": \"user\","
                   + " \"Item\": { "
                   + "\"password\":{\"S\": \"12345\"},"
                   + "\"userid\": {\"S\": \"chloe\"},"
                   + "\"points\":{\"N\": \"23\"}}}";

       String endpoint = "https://0m69exe6q7.execute-api.us-east-1.amazonaws.com/prod/tinydb";
       CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(endpoint);
        httpPut.setHeader("Content-Type", "application/json");
        HttpEntity entity = null;
        try {
            entity = new ByteArrayEntity(item.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPut.setEntity(entity);
        //httpPut.setEntity(item);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpPut);
            System.out.println("response1" + response1.getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static public void main(String[] args){

        putItem();
    }


    static public void connectWaston(){

        try {
            String url = "https://drive.google.com/open?id=0B7MtYWjTFT4RS0s4RnM1NGtCbDA";
            String ibmService = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify"
                    + "?api_key=f18a0a0b0c92f0ba4d92aeef7c0b4bb1bbdce959"
                    + "&url=" + url
                    + "&version=2015-12-02";

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(ibmService);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);


            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();

            // do something useful with the response body
            // and ensure it is fully consumed
            //EntityUtils.consume(entity1);
            String result = EntityUtils.toString(entity1);



            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(result);

            System.out.println("root:" + root);
            //root.forEach(node -> {node.size()});

        } catch(Exception e){
        }
    }

}
