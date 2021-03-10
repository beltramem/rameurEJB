package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entity.Activite_distance;
import ear.entity.Activite_duree;
import ear.entity.Activite_libre;
import ear.entity.Activite_series;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class TypeActiviteRestfulClient {

    private CloseableHttpClient httpClient;
    //private static String BASE_URI = "http://172.16.49.175:8080/RameurEJB-web/rs/TypeActiviteService";
    private static  String BASE_URI = "http://localhost:8080/RameurEJB-web/rs/TypeActiviteService";

    public TypeActiviteRestfulClient()
    {
        this.httpClient = new DefaultHttpClient();
    }

    public List<Activite_duree> getActiviteDuree() throws Exception {
        try {
            String uri = BASE_URI + "/getActiviteDuree";

            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            Gson gson = new GsonBuilder().create();

            Type listType = new TypeToken<ArrayList<Activite_duree>>(){}.getType();
            List<Activite_duree> data = gson.fromJson(apiOutput, listType);
            return data;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    public List<Activite_distance> getActiviteDistance() throws Exception {
        try {
            String uri = BASE_URI + "/getActiviteDistance";

            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            Gson gson = new GsonBuilder().create();

            Type listType = new TypeToken<ArrayList<Activite_distance>>(){}.getType();
            List<Activite_distance> data = gson.fromJson(apiOutput, listType);
            return data;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    public List<Activite_libre> getActiviteLibre() throws Exception {
        try {
            String uri = BASE_URI + "/getActiviteLibre";

            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            Gson gson = new GsonBuilder().create();

            Type listType = new TypeToken<ArrayList<Activite_libre>>(){}.getType();
            List<Activite_libre> data = gson.fromJson(apiOutput, listType);
            return data;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    public List<Activite_series> getActiviteSeries() throws Exception {
        try {
            String uri = BASE_URI + "/getActiviteSeries";

            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            Gson gson = new GsonBuilder().create();

            Type listType = new TypeToken<ArrayList<Activite_series>>(){}.getType();
            List<Activite_series> data = gson.fromJson(apiOutput, listType);
            return data;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }
}
