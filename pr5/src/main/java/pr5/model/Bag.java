package pr5.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;


/**
 * Класс сумки
 */
@Entity
public class Bag {
  /**
   * ID сумки
   */
  @jakarta.persistence.Id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  /**
   * Бренд сумки
   */
  @Column(name = "brand")
  private String brand;
  /**
   * Материал сумки
   */
  @Column(name = "material")
  private String material;
  /**
   * Тип сумки
   */
  @Column(name = "type")
  private String type;
  /**
   * Цена сумки
   */
  @Column(name = "cost")
  private Integer cost;
  /**
   * Объём сумки
   */
  @Column(name = "volume")
  private Double volume;

  public Bag(){}

  public Bag(String brand, String material, String type, Integer cost, Double volume){
    this.id = null;
    this.brand = brand;
    this.material = material;
    this.type = type;
    if (cost >= 0) this.cost = cost;
    if (volume >= 0) this.volume = volume;
  }
  public Bag(Integer id, String brand, String material, String type, Integer cost, Double volume){
    this.id = id;
    this.brand = brand;
    this.material = material;
    this.type = type;
    if (cost >= 0) this.cost = cost;
    if (volume >= 0) this.volume = volume;
  }

  public Double getVolume() {
    return volume;
  }

  public void setVolume(Double volume) {
    this.volume = volume;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Bag{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", material='" + material + '\'' +
            ", type='" + type + '\'' +
            ", cost=" + cost +
            ", volume=" + volume +
            '}';
  }
}
