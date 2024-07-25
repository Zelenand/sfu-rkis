package ru.pr2;

/**
 * Класс портфеля
 */
public class SchoolBag {
  private SchoolSupplie supplie1;
  private SchoolSupplie supplie2;
  private SchoolSupplie supplie3;

  public SchoolBag(SchoolSupplie supplie1, SchoolSupplie supplie2, SchoolSupplie supplie3) {
    this.supplie1 = supplie1;
    this.supplie2 = supplie2;
    this.supplie3 = supplie3;
  }

  /**
   * Перечисляет что внутри портфеля
   */
  public void whatIsInside(){
    System.out.println("В портфеле лежит " + supplie1.getDescription());
    System.out.println("Также, в портфеле лежит " + supplie2.getDescription());
    System.out.println("А ещё в портфеле лежит " + supplie3.getDescription());
  }

}
