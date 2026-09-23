package com.ruba.borrowbox.dto;

public class ItemResponse {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private double pricePerDay;
    private String conditions;
    private boolean available;

    public ItemResponse() {
    }

    public ItemResponse(
            Integer id,
            String name,
            String category,
            String description,
            double pricePerDay,
            String conditions,
            boolean available)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.conditions = conditions;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public double getPricePerDay() {
        return pricePerDay;
    }
    public String getConditions() {
        return conditions;
    }
    public boolean isAvailable() {
        return available;
    }
}