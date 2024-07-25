package pr8_message_client.receiver;

import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Properties;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pr8_message_client.model.MyMessage;

import java.util.Objects;

@Component
public class Receiver {

  @RabbitListener(queues = "bag-queue", containerFactory =
          "rabbitListenerContainerFactory")
  public void listen(MyMessage message) throws MessagingException, UnsupportedEncodingException {
    String message_type = message.getType();
    if (Objects.equals(message_type, "add")){
      System.out.println("Received message: " + message.getMessage() + message.getBag());
    } else if (Objects.equals(message_type, "edit")) {
      System.out.println("Received message: " + message.getMessage() + message.getBag());
    } else if (Objects.equals(message_type, "delete")) {
      System.out.println("Received message: " + message.getMessage());
    } else if (Objects.equals(message_type, "buy")) {
      System.out.println("Received message: " + message.getMessage() + message.getBag());
    } else {
      System.out.println("Received message: " + message.getMessage() + message.getBag());
    }
  }
}
