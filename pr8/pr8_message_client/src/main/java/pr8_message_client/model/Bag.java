package pr8_message_client.model;

import java.io.Serializable;


public class Bag implements Serializable {

    /**
     * ID сумки
     */
    private Long id;

    /**
     * Бренд сумки
     */
    private String brand;
    /**
     * Материал сумки
     */
    private String material;
    /**
     * Тип сумки
     */
    private String type;
    /**
     * Цена сумки
     */
    private Integer cost;
    /**
     * Объём сумки
     */
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
