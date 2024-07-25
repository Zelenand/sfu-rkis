package org.example;

import org.example.entity.Bag;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new
            AnnotationConfigApplicationContext(SpringConfig.class);
    BagDao bagDao = context.getBean("bagDao", BagDao.class);
    Bag bag = new Bag("Prada" , "leather", "typical bag 2", 200, 10.0);
    bagDao.insert(bag);
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    int func;
    while (work) {
      System.out.println("""
            Функции программы:
            1 - Добавление сумки
            2 - Вывести все сумки
            3 - Редактирование записи сумки
            4 - Удалить запись сумки
            5 - Вывести все сумки с ценой ниже указаной
            6 - Выход
            Введите номер функции:""");
      func = 0;
      if (scanner.hasNextInt()) {
        func = scanner.nextInt();
      } else {
        System.out.println("Неккоректный ввод");
        scanner.next();
        continue;
      }
      switch (func) {
        case 1 -> {
          bagDao.insert(Input(scanner));
        }
        case 2 -> {
          Output(bagDao.findAll());
        }
        case 3 -> {
          System.out.println("Введите id записи для изменения");
          int id;
          if (scanner.hasNextInt()){
            id = scanner.nextInt();
          } else {
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          if (id < 0){
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          bagDao.update(id, Input(scanner));
        }
        case 4 -> {
          System.out.println("Введите id");
          int id;
          if (scanner.hasNextInt()){
            id = scanner.nextInt();
          } else {
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          if (id < 0){
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          bagDao.remove(id);
        }
        case 5 -> {
          System.out.println("Введите цену");
          int cost;
          if (scanner.hasNextInt()){
            cost = scanner.nextInt();
          } else {
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          if (cost < 0){
            throw new IllegalArgumentException("Неккоректный ввод");
          }
          Output(bagDao.findAllWithCostLower(cost));
        }
        case 6 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
    context.close();
  }

  /**
   * Ввод данных
   * @param scanner сканер значений с консоли
   * @return новый объект-сумка
   */
  public static Bag Input(Scanner scanner) {
    String brand;
    String material;
    String type;
    int cost;
    double volume;
    System.out.println("Введите бренд");
    scanner.nextLine();
    if (scanner.hasNextLine()){
      brand = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите материал");
    if (scanner.hasNextLine()){
      material = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите тип");
    if (scanner.hasNextLine()){
      type = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите объём");
    if (scanner.hasNextDouble()){
      volume = scanner.nextFloat();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (volume < 0){
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите цену");
    if (scanner.hasNextInt()){
      cost = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (cost < 0){
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    return new Bag(brand, material, type, cost, volume);
  }

  /**
   * Вывод в консоль списка сумок
   * @param bags список сумок
   */
  public static void Output(List<Bag> bags){
    for(Bag bag : bags) {
      System.out.println(bag.toString());
    }
  }
}
