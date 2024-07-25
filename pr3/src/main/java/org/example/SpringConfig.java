package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Класс конфигурации Spring контекста
 */
@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:prop.properties")
public class SpringConfig {
  @Autowired
  Environment env;

  @Bean
  public Pencil pencilBean(){
    return new Pencil("Red");
  }

  @Bean
  public Notebook notebookBean(){
    return new Notebook(env.getProperty("notebook.value"));
  }

  @Bean
  public Textbook textbookBean(){
    return Textbook.getTextbook();
  }

  @Bean
  public SchoolBag schoolBagBean(){
    return new SchoolBag(notebookBean(), pencilBean(), textbookBean());
  }

}
