package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entity.Course;
import ear.entity.Utilisateur;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CourseRestfulClient {
    private CloseableHttpClient httpClient;
    private  static String BASE_URI = "http://172.16.49.175:8080/RameurEJB-web/rs/CourseService";

    public CourseRestfulClient() {
        this.httpClient = new DefaultHttpClient();
    }

    public ear.entity.Course creationCourse(int type_activite, int etat, String participant) throws IOException {
        try {
            String uri = BASE_URI + "/creationCourse" + "/" + type_activite + "/" + etat + "/" + participant;
            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            System.out.println(apiOutput);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            Course data = gson.fromJson(apiOutput, Course.class);
            return data;
        } finally {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }

    public ear.entity.Course getCourse(int id_course) throws IOException {
        try {
            String uri = BASE_URI + "/getCourse" + "/" + id_course ;
            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            System.out.println(apiOutput);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            Course data = gson.fromJson(apiOutput, Course.class);
            return data;
        } finally {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }

    public void rejoindreCourse(int id_course, Utilisateur u) throws IOException {
        String uri = BASE_URI + "/rejoindreCourse" + "/" + id_course+"/"+ u.getIdentifiant();
        HttpPut httpPut = new HttpPut(uri);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");


        System.out.println("Executing request " + httpPut.getRequestLine());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
            };
        String responseBody = httpClient.execute(httpPut, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
    }

}

