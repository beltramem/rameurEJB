package ear.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ear.entity.Mesure;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SenderQueu {

    private String queue;

    public SenderQueu(String queue)
    {
        this.queue = queue;
    }

    public void send_string(String message ) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.49.175");
        factory.setPort(5672);
        factory.setUsername("rameur");
        factory.setPassword("rameur");

        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queue, false, false, false, null);
            channel.basicPublish("",this.queue,false,null,message.getBytes(StandardCharsets.UTF_8));
            //System.out.println("message envoye");
        }
    }

    public void send_mesure(Mesure mesure) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.49.175");
        factory.setPort(5672);
        factory.setUsername("rameur");
        factory.setPassword("rameur");

        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queue, false, false, false, null);
            byte[] mesureByte = SerializationUtils.serialize(mesure);
            channel.basicPublish("",this.queue,false,null,mesureByte);
            //System.out.println("obj envoye");
        }
        catch (Exception exe){
            exe.printStackTrace();
        }
    }
}
