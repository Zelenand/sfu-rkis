package pr8_message_client.model;

import java.io.Serializable;

public class MyMessage implements Serializable {
  private String message;
  private String type;
  private Bag bag;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Bag getBag() {
    return bag;
  }

  public void setBag(Bag bag) {
    this.bag = bag;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public MyMessage() {
  }

  public MyMessage(String type, String message) {
    this.type = type;
    this.message = message;
  }

  public MyMessage(String type, String message, Bag bag) {
    this.type = type;
    this.message = message;
    this.bag = bag;
  }
}

