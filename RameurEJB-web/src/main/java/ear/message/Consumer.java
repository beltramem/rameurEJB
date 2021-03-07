package ear.message;

import com.rabbitmq.client.*;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            channel.queueDeclare("hello-world",false,false,false,null);

        System.out.println("w8 for message");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                byte[] byteMessage = delivery.getBody();
                System.out.println("message recu");
                Object mesure = SerializationUtils.deserialize(byteMessage);
                System.out.println(" [x] Received '" + mesure.toString() + "'");
            }
        };
            /*String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");*/
        channel.basicConsume("hello-world", true, deliverCallback, consumerTag -> { });

    }
}
