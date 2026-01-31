package com.wipro.eshop.service; 
import java.util.List; 
 
import com.wipro.eshop.exception.CartEmptyException; 
import com.wipro.eshop.model.MenuItem; 
 
public interface CartService { 
 void addCartItem(long userId, long menuItemId); 
    List<MenuItem> getAllCartItems(long userId) throws CartEmptyException; 
    void removeCartItem(long userId, long menuItemId); 
     
} 
package com.wipro.eshop.service; 
  
 import java.util.List; 
 import com.wipro.eshop.dao.CartDao; 
 import com.wipro.eshop.dao.CartDaoSqlImpl; 
 import com.wipro.eshop.model.MenuItem; 
 import com.wipro.eshop.exception.CartEmptyException; 
 public class CartServiceImpl implements CartService 
 { 
  private CartDao cartDao; 
  public CartServiceImpl() { 
         this.cartDao = new CartDaoSqlImpl(); 
     } 
  @Override 
     public void addCartItem(long userId, long menuItemId) { 
         cartDao.addCartItem(userId, menuItemId); 
     } 
  @Override 
     public List<MenuItem> getAllCartItems(long userId) throws 
CartEmptyException { 
         return cartDao.getAllCartItems(userId); 
     } 
  @Override 
     public void removeCartItem(long userId, long menuItemId) { 
         cartDao.removeCartItem(userId, menuItemId); 
     } 
 } 
