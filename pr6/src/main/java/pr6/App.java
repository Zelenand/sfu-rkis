package pr6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n")) {
                args[i] = "--spring.datasource.username=" + args[i + 1];
                i++;
            } else if (args[i].equals("-p")) {
                args[i] = "--spring.datasource.password=" + args[i + 1];
                i++;
            } else if (args[i].equals("-u")) {
            args[i] = "--spring.datasource.url=" + args[i + 1];
            i++;
        }
        }

        SpringApplication.run(App.class, args);
    }
}