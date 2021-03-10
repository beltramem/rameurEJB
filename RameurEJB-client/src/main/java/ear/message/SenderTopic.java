package ear.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ear.entity.Mesure;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SenderTopic {
    private static final String EXCHANGE_NAME = "topic_activite";
    private String queue;

    public  SenderTopic(String queue)
    {
        this.queue = queue;
    }
    public void send_mesure(Mesure mesure, String routingKey)  throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.49.175");
        factory.setPort(5672);
        factory.setUsername("rameur");
        factory.setPassword("rameur");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");



            byte[] mesureByte = SerializationUtils.serialize(mesure);
            channel.basicPublish(EXCHANGE_NAME,routingKey, null,mesureByte);
            //System.out.println(" [x] Sent '" + routingKey + "':'" + mesure.toString() + "'");
        }
 }
}
