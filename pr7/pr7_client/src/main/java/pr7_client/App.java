package pr7_client;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class App
{
  public static void main( String[] args ){
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    int func;
    while (work) {
      System.out.println("""
              Функции программы:
              0 - Репрезентативный тестовый пример
              1 - post сумку
              2 - delete сумку по id
              3 - put сумку по id
              4 - get всех сумок
              5 - get сумку по id
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
        case 0 -> {
          postBag(new Bag("example_1", "example_1", "example_1", 111, 111.0));
          System.out.println("Только что был выполнен post запрос по добавлению Bag(\"example_1\", \"example_1\", \"example_1\", 111, 111.0)");
          System.out.println("Результат можно увидеть открыв http://localhost:8081/bags");
          System.out.println("Для продолжения нажмите enter");
          scanner.nextLine();
          scanner.nextLine();

          int id = getLastId();
          try {
            putBag(id, new Bag("example_2", "example_2", "example_2", 222, 222.0));
          } catch (BagException e) {
            System.out.println(e.getMessage());
          }
          System.out.println("Только что был выполнен put запрос по изменению последнего добавленного bag на Bag(\"example_2\", \"example_2\", \"example_2\", 222, 222.0)");
          System.out.println("Результат можно увидеть открыв http://localhost:8081/bags");
          System.out.println("Для продолжения нажмите enter");
          scanner.nextLine();

          System.out.println("Только что был выполнен get запрос по получению нашего bag");
          System.out.println("Результат:");
          System.out.println(getBag(id));
          System.out.println("Для продолжения нажмите enter");

          try {
            deleteBag(id);
          } catch (BagException e) {
            System.out.println(e.getMessage());
          }
          System.out.println("Только что был выполнен delete запрос по удалению нашего bag");
          System.out.println("Результат можно увидеть открыв http://localhost:8081/bags");
          System.out.println("Для окончания репрезентативного примера нажмите enter");
          scanner.nextLine();
        }
        case 1 -> {
          try {
            postBag(Input(scanner));
          } catch (IllegalArgumentException e) {
            scanner.nextLine();
            System.out.println(e.getMessage());
          }
        }
        case 2 -> {
          int id = 0;
          System.out.println("Введите id");
          if (scanner.hasNextInt()) {
            id = scanner.nextInt();
          } else {
            System.out.println("Неккоректный ввод");
            scanner.next();
            continue;
          }
          try {
            deleteBag(id);
          } catch (BagException e) {
            System.out.println(e.getMessage());
          }
        }
        case 3 -> {
          int id = 0;
          System.out.println("Введите id");
          if (scanner.hasNextInt()) {
            id = scanner.nextInt();
          } else {
            System.out.println("Неккоректный ввод");
            scanner.next();
            continue;
          }
          try {
            putBag(id, Input(scanner));
          } catch (BagException e) {
            System.out.println(e.getMessage());
          }
        }
        case 4 -> {
          System.out.println(getBags());
        }
        case 5 -> {
          int id = 0;
          System.out.println("Введите id");
          if (scanner.hasNextInt()) {
            id = scanner.nextInt();
          } else {
            System.out.println("Неккоректный ввод");
            scanner.next();
            continue;
          }
          System.out.println(getBag(id));
        }
        case 6 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  public static Bag Input(Scanner scanner) {
    String brand;
    int cost;
    String material;
    String type;
    double volume;

    System.out.println("Введите бренд");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      brand = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    System.out.println("Введите материал");
    if (scanner.hasNextLine()) {
      material = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    System.out.println("Введите тип");
    if (scanner.hasNextLine()) {
      type = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    System.out.println("Введите цену");
    if (scanner.hasNextInt()) {
      cost = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (cost < 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    System.out.println("Введите объём");
    if (scanner.hasNextDouble()) {
      volume = scanner.nextDouble();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (volume < 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    return new Bag(200L, brand, material, type, cost, volume);
  }

  public static String getBag(int id) {
    return new RestTemplate().getForObject("http://localhost:8081/bags/{id}", String.class, id);
  }

  public static String getBags() {
    return new RestTemplate().getForObject("http://localhost:8081/bags", String.class);
  }

  public static void putBag(int id, Bag bag) throws BagException {
    try {
      String url = "http://localhost:8081/bags/" + id;
      new RestTemplate().put(new URI(url), bag);
    } catch (URISyntaxException e) { throw new BagException("Unable to update Bag", e);
    }
  }

  public static void deleteBag(long id) throws BagException {
    try {
      new RestTemplate().delete(new URI("http://localhost:8081/bags/" + id));
    } catch (URISyntaxException e) { throw new BagException("Unable to delete Bag", e);}
  }

  public static void postBag(Bag bag) {
    RestTemplate rest = new RestTemplate();
    System.out.println(bag);
    rest.postForObject("http://localhost:8081/bags", bag, Bag.class);
  }

  public static int getLastId() {
    RestTemplate rest = new RestTemplate();
    int last_id = rest.getForObject("http://localhost:8081/bags/last_id", Integer.class);
    return last_id;
  }
}
