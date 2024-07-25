package pr8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "bag")
public class Bag implements Serializable {

    /**
     * ID сумки
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Бренд сумки
     */
    @Column(name = "brand")
    @Size(min = 2, message = "Длина не менее 2х символов")
    private String brand;
    /**
     * Материал сумки
     */
    @Column(name = "material")
    @Size(min = 2, message = "Длина не менее 2х символов")
    private String material;
    /**
     * Тип сумки
     */
    @Column(name = "type")
    @Size(min = 2, message = "Длина не менее 2х символов")
    private String type;
    /**
     * Цена сумки
     */
    @Column(name = "cost")
    @Min(value = 0, message = "Количество должно быть не меньше чем 0")
    private Integer cost;
    /**
     * Объём сумки
     */
    @Column(name = "volume")
    @DecimalMin(value = "0", message = "Цена должно быть больше чем 0", inclusive = false)
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
    public Bag(Long id, String brand, String material, String type, Integer cost, Double volume){
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
