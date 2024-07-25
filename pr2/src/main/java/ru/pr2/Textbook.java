package ru.pr2;

/**
 * Класс учебника
 */
public class Textbook implements SchoolSupplie {
  /**
   * Предмет учебника
   */
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

  /**
   * Описывает учебник
   * @return описание учебник
   */
  @Override
  public String getDescription() {
    return "учебник по " + subject;
  }
}
