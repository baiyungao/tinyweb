package com.iiibc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Calendar;

/**
 * Created by gaob on 2/13/2017.
 */

@WebServlet(value="/accept", name="AcceptFileServlet")
public class AcceptFileServlet extends HttpServlet {


    private static final String SAVE_DIR = "uploadFiles";
    private static final String WEB_URL = "http://odataxpt.cloudapp.net/green/uploadFiles/";

    /**
     * handles file upload
     */

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String path = request.getParameter("destination");
        //final Part filePart = request.getPart("file");
        final String fileName = Calendar.getInstance().getTimeInMillis() + ".jpg";

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(savePath + File.separator
                    + fileName));
            filecontent = request.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + savePath);

            //call IBM service

            String url = WEB_URL + fileName;

            //Sample images; url https://drive.google.com/open?id=0B7MtYWjTFT4RS0s4RnM1NGtCbDA"
            String ibmService = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify"
                    + "?api_key=f18a0a0b0c92f0ba4d92aeef7c0b4bb1bbdce959"
                    + "&url=" + url
                    + "&version=2015-12-02";
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(ibmService);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);

            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();

                // do something useful with the response body
                // and ensure it is fully consumed
                //EntityUtils.consume(entity1);
                String result = EntityUtils.toString(entity1);



                ObjectMapper mapper = new ObjectMapper();
                JsonNode roots = mapper.readTree(result);




                writer.print(result);
            } finally {
                response1.close();
            }



        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());


        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

}
