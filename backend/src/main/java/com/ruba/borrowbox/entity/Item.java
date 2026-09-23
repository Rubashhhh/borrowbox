package com.ruba.borrowbox.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Item{

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String category;
    private String description;
    @PositiveOrZero
    private double pricePerDay;
    private String conditions;
    private boolean available;

    public Item() {
    }

    public Item(Integer id, String name, String category, String description, double pricePerDay, String conditions, boolean available)
    {
        this.id=id;
        this.name=name;
        this.category=category;
        this.description=description;
        this.pricePerDay=pricePerDay;
        this.conditions=conditions;
        this.available=available;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer i){
        id=i;
    }
    public String getName(){
        return name;
    }
    public void setName(String nm){
         name=nm;
    }
    public String getCategory(){
        return category ;
    }
    public void setCategory(String ctgry){
        category=ctgry;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String desc){
        description=desc;
    }
    public double getPricePerDay(){
    return pricePerDay;
    }
    public void setPricePerDay(double ppd){
        pricePerDay=ppd;
    }
    public String getConditions(){
    return conditions ;
    }
    public void setConditions(String cndtn){
        conditions=cndtn;
    }
    public boolean isAvailable(){
    return available;
    }
    public void setAvailable(boolean avlbl ){
        available=avlbl;
    }
}