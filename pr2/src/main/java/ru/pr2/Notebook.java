package ru.pr2;

/**
 * Класс тетради
 */
public class Notebook implements SchoolSupplie {
  /**
   * Тип тетради
   */
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

  /**
   * Описывает тетрадь
   * @return описание тетради
   */
  @Override
  public String getDescription() {
    return "тетрадь " + type;
  }
}
