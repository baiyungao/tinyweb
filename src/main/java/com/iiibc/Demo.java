package com.iiibc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by gaob on 2/12/2017.
 */
public class Demo {

    static public void main(String[] args){

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
