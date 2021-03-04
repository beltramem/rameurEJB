package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entities.Utilisateur;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

;

public class UtilisateurRestfulClient {

    private DefaultHttpClient httpClient;
    private static String BASE_URI = "http://127.0.0.1:8080/RameurEJB-web/rs/UtilisateurService";

    public UtilisateurRestfulClient() {
        this.httpClient = new DefaultHttpClient();
    }

    public Utilisateur connexion(String identifiant, String mdp) throws Exception
    {
        try {
            String uri = BASE_URI + "/connexion" + "/" + identifiant + "/" + mdp;
            System.out.println(uri);


            HttpGet getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            Utilisateur data = gson.fromJson(apiOutput, Utilisateur.class);
            return data;
        }
        finally
        {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }



}
