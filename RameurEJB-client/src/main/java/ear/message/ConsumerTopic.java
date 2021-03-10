package ear.message;

import com.rabbitmq.client.*;

import javax.print.attribute.standard.MediaSize;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import ear.entity.Mesure;
import org.apache.commons.lang.SerializationUtils;


public class ConsumerTopic {
    private static final String EXCHANGE_NAME = "topic_activite";
    private String username;
    private int activiteID;

    public ConsumerTopic(String username, int activiteID) {
        this.username = username;
        this.activiteID = activiteID;
    }

    public void getMessage() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.49.175");
        factory.setPort(5672);
        factory.setUsername("rameur");
        factory.setPassword("rameur");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = "adversaire_data"+this.username;
        channel.queueDeclare(queueName, false, false, false, null);
        String bindingKey = "course."+this.activiteID+".#";
        channel.queueBind(queueName,EXCHANGE_NAME,bindingKey);

        DeliverCallback deliverCallback = new DeliverCallback() {
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                byte[] byteMessage = delivery.getBody();
                Mesure mesure = (Mesure) SerializationUtils.deserialize(byteMessage);

                if(!mesure.getIdentifiant_utilisateur().equals(username)) {
                    System.out.println(" [x] Received '" + mesure.toString() + "'");
                }

            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }
}
