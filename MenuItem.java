package com.wipro.eshop.model; 
 
import java.util.Date; 
 
public class MenuItem { 
    private long id; 
    private String name; 
    private float price; 
    private boolean active; 
    private Date dateOfLaunch; 
    private String category; 
    private boolean freeDelivery; 
 
    public MenuItem(long id, String name, float price, boolean active, 
                    Date dateOfLaunch, String category, boolean freeDelivery) { 
        this.id = id; 
        this.name = name; 
        this.price = price; 
        this.active = active; 
        this.dateOfLaunch = dateOfLaunch; 
        this.category = category; 
        this.freeDelivery = freeDelivery; 
    } 
 
    public long getId() { return id; } 
    public void setId(long id) { this.id = id; } 
 
    public String getName() { return name; } 
    public void setName(String name) { this.name = name; } 
 
    public float getPrice() { return price; } 
    public void setPrice(float price) { this.price = price; } 
 
    public boolean isActive() { return active; } 
    public void setActive(boolean active) { this.active = active; } 
 
    public Date getDateOfLaunch() { return dateOfLaunch; } 
    public void setDateOfLaunch(Date dateOfLaunch) { this.dateOfLaunch = 
dateOfLaunch; } 
 
    public String getCategory() { return category; } 
    public void setCategory(String category) { this.category = category; } 
 
    public boolean isFreeDelivery() { return freeDelivery; } 
    public void setFreeDelivery(boolean freeDelivery) { this.freeDelivery = 
freeDelivery; } 
 
    @Override 
    public String toString() { 
        return "MenuItem{" + 
                "id=" + id + 
                ", name='" + name + '\'' + 
                ", price=" + price + 
                ", active=" + active + 
                ", dateOfLaunch=" + dateOfLaunch + 
                ", category='" + category + '\'' + 
                ", freeDelivery=" + freeDelivery + 
                '}'; 
    } 
 
    @Override 
    public boolean equals(Object obj) { 
        if (this == obj) return true; 
        if (!(obj instanceof MenuItem)) return false; 
        MenuItem other = (MenuItem) obj; 
        return this.id == other.id; 
    } 
} 
