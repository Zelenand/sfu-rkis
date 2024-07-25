package ru.pr2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new
            ClassPathXmlApplicationContext("applicationContext.xml");
    SchoolBag schoolBag = context.getBean("schoolBagBean", SchoolBag.class);
    schoolBag.whatIsInside();
    context.close();
  }
}
