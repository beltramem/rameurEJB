package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entity.Activite_duree;
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
    private static String BASE_URI = "http://127.0.0.1:8080/RameurEJB-web/rs/TypeActiviteService";

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
}
