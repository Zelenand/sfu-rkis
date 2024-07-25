package ru.pr2;

/**
 * Класс карандаша
 */
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

  /**
   * Описывает карандаш
   * @return описание карандаша
   */
  @Override
  public String getDescription() {
    return "карандаш цвета " + color;
  }
}
