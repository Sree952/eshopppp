package com.wipro.eshop.service; 
 
import java.util.List; 
import com.wipro.eshop.dao.MenuItemDao; 
import com.wipro.eshop.dao.MenuItemDaoSqlImpl; 
import com.wipro.eshop.model.MenuItem; 
 
public class MenuItemServiceImpl implements MenuItemService { 
 
    private MenuItemDao menuItemDao; 
 
    public MenuItemServiceImpl() { 
        this.menuItemDao = new MenuItemDaoSqlImpl(); 
    } 
 
    @Override 
    public List<MenuItem> getMenuItemListAdmin() { 
        return menuItemDao.getMenuItemListAdmin(); 
    } 
 
    @Override 
    public List<MenuItem> getMenuItemListCustomer() { 
        return menuItemDao.getMenuItemListCustomer(); 
    } 
 
    @Override 
    public MenuItem getMenuItem(long menuItemId) { 
        return menuItemDao.getMenuItem(menuItemId); 
    } 
 
    @Override 
    public void modifyMenuItem(MenuItem menuItem) { 
        menuItemDao.modifyMenuItem(menuItem); 
    } 
 
    @Override 
    public long addMenuItem(MenuItem newItem) { 
        return menuItemDao.addMenuItem(newItem); 
    } 
} 
