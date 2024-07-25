package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс портфеля
 */
@Component("schoolBagBean")
public class SchoolBag {
  private SchoolSupplie supplie1;
  private SchoolSupplie supplie2;
  private SchoolSupplie supplie3;

  @Autowired
  public SchoolBag(@Qualifier("notebookBean") SchoolSupplie supplie1, @Qualifier("pencilBean") SchoolSupplie supplie2, @Qualifier("textbookBean") SchoolSupplie supplie3) {
    this.supplie1 = supplie1;
    this.supplie2 = supplie2;
    this.supplie3 = supplie3;
  }

  @PostConstruct
  public void init(){
    System.out.println("Портфель теперь существует");
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Портфель больше не существует");
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

