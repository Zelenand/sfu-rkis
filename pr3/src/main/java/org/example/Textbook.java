package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс учебника
 */
@Component("textbookBean")
public class Textbook implements SchoolSupplie {
  /**
   * Предмет учебника
   */
  @Value("ОБЖ")
  private String subject;

  public Textbook(){}

  public Textbook(String subject){
    this.subject = subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

  @PostConstruct
  public void init(){
    System.out.println("Учебник теперь существует");
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Учебник больше не существует");
  }

  public static Textbook getTextbook(){
    System.out.println("Фабричный метод. Учебник!");
    return new Textbook();
  }


  /**
   * Описывает учебник
   * @return описание учебник
   */
  @Override
  public String getDescription() {
    return "учебник по " + subject;
  }
}