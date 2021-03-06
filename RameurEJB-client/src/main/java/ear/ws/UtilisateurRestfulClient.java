package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entities.Utilisateur;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

;import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilisateurRestfulClient {

    private CloseableHttpClient httpClient;
    private static String BASE_URI = "http://127.0.0.1:8080/RameurEJB-web/rs/UtilisateurService";

    public UtilisateurRestfulClient() {
        this.httpClient = HttpClients.createDefault();
    }

    public Utilisateur connexion(String identifiant, String mdp) throws Exception
    {
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

    public void creationCompte(String identifiant, String mdp, String nom, String prenom, float taille, float poids, Date date_naissance) throws Exception
    {
        /*CloseableHttpClient client = HttpClients.createDefault();

        String URI = BASE_URI + "/creationCompte";
            HttpPost httpPost = new HttpPost(URI);

            System.out.println(URI);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("identifiant", identifiant));
            params.add(new BasicNameValuePair("mdp", mdp));
            params.add(new BasicNameValuePair("nom", nom));
            params.add(new BasicNameValuePair("prenom", prenom));
            params.add(new BasicNameValuePair("taille", String.valueOf(taille)));
            params.add(new BasicNameValuePair("poids", String.valueOf(poids)));
            params.add(new BasicNameValuePair("date_naissance", String.valueOf(date_naissance)));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("aled");*/


        HttpPost post = new HttpPost(BASE_URI+"/creationCompte");

        // add request parameter, form parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("identifiant", identifiant));
        params.add(new BasicNameValuePair("mdp", mdp));
        params.add(new BasicNameValuePair("nom", nom));
        params.add(new BasicNameValuePair("prenom", prenom));
        params.add(new BasicNameValuePair("taille", String.valueOf(taille)));
        params.add(new BasicNameValuePair("poids", String.valueOf(poids)));
        params.add(new BasicNameValuePair("date_naissance", String.valueOf(date_naissance)));

        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println("pa");

            System.out.println(EntityUtils.toString(response.getEntity()));
        }


    }


}
