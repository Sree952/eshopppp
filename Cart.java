package com.wipro.eshop.model; 
 
import java.util.List; 
 
public class Cart { 
    private List<MenuItem> menuItemList; 
    private double total; 
 
    public Cart(List<MenuItem> menuItemList, double total) { 
        this.menuItemList = menuItemList; 
        this.total = total; 
    } 
 
    public List<MenuItem> getMenuItemList() { 
        return menuItemList; 
    } 
 
    public void setMenuItemList(List<MenuItem> menuItemList) { 
        this.menuItemList = menuItemList; 
    } 
 
    public double getTotal() { 
        return total; 
    } 
 
    public void setTotal(double total) { 
        this.total = total; 
    } 
 
    @Override 
    public String toString() { 
        return "Cart{" + 
                "menuItemList=" + menuItemList + 
                ", total=" + total + 
                '}'; 
    } 
} 
