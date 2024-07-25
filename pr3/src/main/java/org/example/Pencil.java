package org.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс карандаша
 */
@Component("pencilBean")
public class Pencil implements SchoolSupplie {
  /**
   * Цвет карандаша
   */
  private String color;

  public Pencil(){}

  public Pencil(String color){
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @PostConstruct
  public void init(){
    System.out.println("Карандаш теперь существует");
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Карандаш больше не существует");
  }

  /**
   * Описывает карандаш
   * @return описание карандаша
   */
  @Override
  public String getDescription() {
    return "карандаш цвета " + color;
  }
}
