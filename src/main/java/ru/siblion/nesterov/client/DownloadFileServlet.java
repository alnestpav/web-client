package ru.siblion.nesterov.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by alexander on 23.01.2017.
 */
public class DownloadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String BASE_URL = "http://192.168.0.164:7001/logreader-1.0.1/resources/restWebService/getFile?filename=log-d2017-01-19-16-20-47-380+0300h1831496568.pdf";

        Client client = ClientBuilder.newClient();

        File outputFile = new File("my.pdf");
        try {
            URL website = new URL(BASE_URL);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(outputFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
