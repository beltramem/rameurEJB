package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entity.Entrainement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EntrainementRestfulClient {
    private CloseableHttpClient httpClient;
    //private  static String BASE_URI = "http://172.16.49.175:8080/RameurEJB-web/rs/EntrainementService";
    private static  String BASE_URI = "http://localhost:8080/RameurEJB-web/rs/EntrainementService";

    public EntrainementRestfulClient() {
        this.httpClient = new DefaultHttpClient();
    }

    public Entrainement creationEntrainement(int type_activite, int etat, String participant) throws IOException {
        try {
            String uri = BASE_URI + "/creationEntrainement" + "/" + type_activite + "/" + etat + "/"+ participant;
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

            Entrainement data = gson.fromJson(apiOutput, Entrainement.class);
            return data;
        }
        finally
        {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }


}
