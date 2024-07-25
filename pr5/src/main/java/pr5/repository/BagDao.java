package pr5.repository;

import pr5.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

/**
 * DAO для взаимодействия с базой данных сумок
 */
@Component
public class BagDao {
  JdbcTemplate jdbcTemplate;
  @Autowired
  public void setDataSource(DataSource dataSource){
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * Получить все сумки
   * @return список сумок
   */
  public List<Bag> findAll(){
    List<Bag> bags = jdbcTemplate.query("select * from bag", new
            BeanPropertyRowMapper<>(Bag.class));
    return bags;
  }

  public Bag findById(int id){
    List<Bag> bags = jdbcTemplate.query(String.format("select * from bag WHERE id = %d", id), new
            BeanPropertyRowMapper<>(Bag.class));
    return bags.get(0);
  }

  /**
   * Добавить сумку в базу данных
   * @param bag объект сумки
   */
  public void insert(Bag bag){
    if (bag.getId() == null){
      jdbcTemplate.update("insert into bag" + "(brand, material, type, cost, volume) "
                      + "values (?,?,?,?,?)",
              bag.getBrand(),
              bag.getMaterial(), bag.getType(), bag.getCost(), bag.getVolume());
    } else{
      jdbcTemplate.update("insert into bag" + "(id, brand, material, type, cost, volume) "
                      + "values (?,?,?,?,?,?)",
              bag.getId(), bag.getBrand(),
              bag.getMaterial(), bag.getType(), bag.getCost(), bag.getVolume());
    }
  }

  /**
   * Изменить запись о сумке
   * @param id ID сумки
   * @param bag новый объект сумки
   */
  public void update(int id, Bag bag){
    Locale.setDefault(Locale.US);
    jdbcTemplate.update(String.format("UPDATE bag SET brand = '%s', material = '%s', type = '%s', cost = %d, volume = %f WHERE id = %d", bag.getBrand(),
            bag.getMaterial(), bag.getType(), bag.getCost(), bag.getVolume(), bag.getId()));
  }

  /**
   * Удалить запись о сумке
   * @param id ID сумки
   */
  public void remove(int id){
    jdbcTemplate.update("DELETE FROM bag WHERE id = " + "(?)", id);
  }

  /**
   * Получить список сумок с ценой ниже указанной
   * @param cost цена
   * @return список сумок с ценой ниже указанной
   */
  public List<Bag> findAllWithCostLower(int cost){
    List<Bag> bags = jdbcTemplate.query(String.format("select * from bag WHERE cost <= %s", cost), new
            BeanPropertyRowMapper<>(Bag.class));
    return bags;
  }
}
