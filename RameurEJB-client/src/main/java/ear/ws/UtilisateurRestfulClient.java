package ear.ws;

import com.google.gson.Gson;
import ear.entities.Utilisateur;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

;

public class UtilisateurRestfulClient {

    public static void main(String[] args) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try
        {
            //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
            //Choice depends on type of method you will be invoking.
            HttpGet getRequest = new HttpGet("http://127.0.0.1:8080/RameurEJB-web/rs/UtilisateurService/connexion/beltramem/123");

            //Set the API media type in http accept header
            //getRequest.addHeader("accept", "application/xml");

            //Send the request; It will immediately return the response in HttpResponse object
            HttpResponse response = httpClient.execute(getRequest);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200)
            {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            //Now pull back the response object
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);

            //Lets see what we got from API
            System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>

            //In realtime programming, you will need to convert this http response to some java object to re-use it.
            //Lets see how to jaxb unmarshal the api response content
            /*JAXBContext jaxbContext = JAXBContext.newInstance(Utilisateur.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Utilisateur user = (Utilisateur) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
            */

            Utilisateur data = new Gson().fromJson(apiOutput, Utilisateur.class);
            System.out.println(data.getIdentifiant());
            //Verify the populated object
            //System.out.println(user.getIdentifiant());

        }
        finally
        {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }

}
