package com.wipro.eshop.dao; 
 
import java.util.List; 
import com.wipro.eshop.model.MenuItem; 
import com.wipro.eshop.exception.CartEmptyException; 
 
public interface CartDao { 
    void addCartItem(long userId, long menuItemId); 
    List<MenuItem> getAllCartItems(long userId) throws CartEmptyException; 
    void removeCartItem(long userId, long menuItemId); 
} 
