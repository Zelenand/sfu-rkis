package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс тетради
 */
@Component("notebookBean")
public class Notebook implements SchoolSupplie {
  /**
   * Тип тетради
   */
  @Value("в клетку")
  private String type;

  public Notebook(){}

  public Notebook(String type){
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @PostConstruct
  public void init(){
    System.out.println("Тетрадь теперь существует");
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Тетрадь больше не существует");
  }

  /**
   * Описывает тетрадь
   * @return описание тетради
   */
  @Override
  public String getDescription() {
    return "тетрадь " + type;
  }
}

