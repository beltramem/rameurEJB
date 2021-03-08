package ear.message;

import com.rabbitmq.client.*;
import ear.entity.Mesure;
import ear.session.MesureLocal;
import org.apache.commons.lang.SerializationUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private String userName;


    // Driver used to connect with the database.
    private static String DRIVER = "org.postgresql.Driver";

    // Database URL.
    private static String URL = "jdbc:postgresql://localhost:5432/rameur";

    // Database username.
    private static String USERNAME = "rameuradmin";

    // Database password.
    private static String PASSWORD = "admin";

    public static Statement connectToPostgres() {

        try {
            Class.forName(DRIVER);
            java.sql.Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = con.createStatement();

            return stmt;
        } catch (Exception e) {
            System.out.println(String.format("Error while connecting to the MySQL [%s]. [%s]", e.getMessage(), e));
        }

        return null;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Consumer(String userName) {
        this.userName = userName;
    }
    public Consumer(){}

    public void getMessage() throws Exception {
        try {

            Statement postgresConnection = connectToPostgres();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            channel.queueDeclare(this.userName, false, false, false, null);

            System.out.println("queu " + this.userName + "w8 for message");

            DeliverCallback deliverCallback = new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery delivery) throws IOException {
                    byte[] byteMessage = delivery.getBody();
                    //System.out.println("message recu");
                    Mesure mesure = (Mesure) SerializationUtils.deserialize(byteMessage);
                    System.out.println(" [x] Received '" + mesure.toString() + "'");
                    String query = "insert into mesure(identifiant_utilisateur,id_course,id_entrainement,date,vitesse,distance_parcourue,calories_brulees,puissance_developpe,rythme_cardiaque) VALUES ('"+mesure.getIdentifiant_utilisateur()+"',"+mesure.getId_course()+","+mesure.getId_entrainement()+",'"+mesure.getDate()+"',"+mesure.getVitesse()+","+mesure.getDistance_parcourue()+","+mesure.getCalories_brulees()+","+mesure.getPuissance_developpe()+","+mesure.getRythme_cardiaque()+")";
                    System.out.println(query);
                    try {
                        postgresConnection.execute(query);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            };
            //String message = new String(delivery.getBody(), "UTF-8");
            //System.out.println(" [x] Received '" + message + "'");
            channel.basicConsume(this.userName, true, deliverCallback, consumerTag -> {
            });
        }catch (Exception e)
        {
            throw new Exception(e);
        }
    }
}
