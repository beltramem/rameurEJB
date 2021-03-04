package ear.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ear.entities.Entrainement;
import ear.entities.Type_activite;
import ear.entities.Utilisateur;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class EntrainementRestfulClient {
    private DefaultHttpClient httpClient;
    private  static String BASE_URI = "http://127.0.0.1:8080/RameurEJB-web/rs/EntrainementService";

    public EntrainementRestfulClient() {
        this.httpClient = new DefaultHttpClient();
    }

    public Entrainement creationEntrainement(int type_activite, int etat) throws IOException {
        try
        {

            HttpPost httpPost = new HttpPost(BASE_URI+"/creationEntrainement");
            String id_type = String.valueOf(type_activite);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type_activite",id_type));
            params.add(new BasicNameValuePair("etat", String.valueOf(etat)));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);

            System.out.println(apiOutput);
            Gson gson = new GsonBuilder().create();

            Entrainement data = gson.fromJson(apiOutput, Entrainement.class);
            return  data;
        }  finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
