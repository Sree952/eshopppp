package com.wipro.eshop.service; 
 
import java.util.List; 
 
import com.wipro.eshop.model.MenuItem; 
 
public interface MenuItemService { 
 List<MenuItem> getMenuItemListAdmin(); 
    List<MenuItem> getMenuItemListCustomer(); 
    MenuItem getMenuItem(long menuItemId); 
    void modifyMenuItem(MenuItem menuItem); 
    long addMenuItem(MenuItem item); 
}
