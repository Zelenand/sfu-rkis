# Практическая работа 3: Spring, внедрение зависимостей. Способ 2
Никита Самарин
КИ21-17/1Б
Вариант: 17. Портфель

## Инструкции по сборке и запуску проекта
Запустить программу можно с помощью комманды:
    ```
    java -jar target\pr3.jar
    ```

Собрать программу с нуля можно с помощью выполнения последовательно комманд:
    ```
    mvn clean
    mvn compile
    mvn package
    mvn install
    ```
## Структура проекта

Проект состоит из следующих файлов:

- `src\main\java\ru\pr2\Main.java`: Главный класс, содержащий метод `main()`, который запускает приложение.

- `src/main/java/org/example/SpringConfig.java`: Класс конфигурации String контекста

- `src\main\java\ru\pr2\SchoolBag.java`: Класс портфеля

- `src\main\java\ru\pr2\SchoolSupplie.java`: Интерфейс школьной принадлежности

- `src\main\java\ru\pr2\Pencil.java`: Класс карандаша, реализует SchoolSupplie

- `src\main\java\ru\pr2\Notebook.java`: Класс тетради, реализует SchoolSupplie

- `src\main\java\ru\pr2\Textbook.java`: Класс учебника, реализует SchoolSupplie

- `src\main\resources\prop.properties`: Файл в котором хранится свойство одного из Bean