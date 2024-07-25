package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new
            AnnotationConfigApplicationContext(SpringConfig.class);
    SchoolBag schoolBag = (SchoolBag) context.getBean("schoolBagBean");
    schoolBag.whatIsInside();
    context.close();
  }
}