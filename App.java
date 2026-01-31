package com.wipro.eshop; 
 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.util.Date; 
import java.util.List; 
import java.util.Scanner; 
 
import com.wipro.eshop.model.MenuItem; 
import com.wipro.eshop.service.MenuItemService; 
import com.wipro.eshop.service.MenuItemServiceImpl; 
import com.wipro.eshop.service.CartService; 
import com.wipro.eshop.service.CartServiceImpl; 
import com.wipro.eshop.dao.ConnectionHandler; 
import com.wipro.eshop.exception.CartEmptyException; 
import com.wipro.eshop.util.DateUtil; 
 
public class App { 
    private static Scanner sc; 
 
    public static void main(String[] args) { 
        sc = new Scanner(System.in); 
        MenuItemService menuItemService = new MenuItemServiceImpl(); 
        CartService cartService = new CartServiceImpl(); 
 
        System.out.println("Welcome to eShop "); 
        System.out.println("Select your role:"); 
        System.out.println("1. Admin"); 
        System.out.println("2. Customer"); 
        System.out.print("Enter choice: "); 
        int roleChoice = sc.nextInt(); 
        sc.nextLine(); 
 
        if (roleChoice == 1) { 
            // Admin Menu 
            while (true) { 
                System.out.println("\n Admin Menu "); 
                System.out.println("1. View All Menu Items"); 
                System.out.println("2. Get Menu Item by ID"); 
                System.out.println("3. Modify Menu Item"); 
                System.out.println("4. Add New Menu Item"); 
                System.out.println("5. Exit"); 
                System.out.print("Enter your choice: "); 
                int choice = sc.nextInt(); 
                sc.nextLine(); 
 
                switch (choice) { 
                    case 1: 
                        List<MenuItem> adminItems = 
menuItemService.getMenuItemListAdmin(); 
                        for (MenuItem item : adminItems) { 
                            System.out.println(item); 
                        } 
                        break; 
 
                    case 2: 
                        System.out.print("Enter Item ID to Retrieve: "); 
                        long getId = sc.nextLong(); 
                        sc.nextLine(); 
                        MenuItem menuItem = menuItemService.getMenuItem(getId); 
                        if (menuItem != null) { 
                            System.out.println(menuItem); 
                        } else { 
                            System.out.println("Item ID does not exist."); 
                        } 
                        break; 
 
                    case 3: 
                        System.out.print("Enter Item ID to Modify: "); 
                        long mid = sc.nextLong(); 
                        sc.nextLine(); 
 
                        MenuItem existingItem = menuItemService.getMenuItem(mid); 
                        if (existingItem == null) { 
                            System.out.println("Item ID not found. Modification 
aborted."); 
                            break; 
                        } 
 
                        System.out.println("Leave field blank to keep existing 
value."); 
 
                        System.out.print("New Name [" + existingItem.getName() + 
"]: "); 
                        String name = sc.nextLine(); 
                        if (name.trim().isEmpty()) name = existingItem.getName(); 
 
                        float price = -1; 
                        while (price <= 0) { 
                            System.out.print("New Price [" + 
existingItem.getPrice() + "]: "); 
                            String priceInput = sc.nextLine(); 
                            if (priceInput.trim().isEmpty()) { 
                                price = existingItem.getPrice(); 
                                break; 
                            } 
                            try { 
                                price = Float.parseFloat(priceInput); 
                                if (price <= 0) System.out.println("Price must be 
greater than 0."); 
                            } catch (NumberFormatException e) { 
                                System.out.println(" Invalid price. Enter a valid 
number."); 
                            } 
                        } 
 
                        System.out.print("Is Active (yes/no) [" + 
(existingItem.isActive() ? "yes" : "no") + "]: "); 
                        String activeInput = sc.nextLine(); 
                        boolean active = activeInput.trim().isEmpty() ? 
existingItem.isActive() 
                                : activeInput.equalsIgnoreCase("yes"); 
 
                        System.out.print("Date of Launch (dd/MM/yyyy) [" + 
                                
DateUtil.convertToString(existingItem.getDateOfLaunch()) + "]: "); 
                        String dateStr = sc.nextLine(); 
                        Date date = dateStr.trim().isEmpty() 
                                ? existingItem.getDateOfLaunch() 
                                : DateUtil.convertToDate(dateStr); 
 
                        System.out.print("Category [" + existingItem.getCategory() 
+ "]: "); 
                        String category = sc.nextLine(); 
                        if (category.trim().isEmpty()) category = 
existingItem.getCategory(); 
 
                        System.out.print("Free Delivery (yes/no) [" + 
                                (existingItem.isFreeDelivery() ? "yes" : "no") + 
"]: "); 
                        String freeDeliveryInput = sc.nextLine(); 
                        boolean freeDelivery = freeDeliveryInput.trim().isEmpty() 
                                ? existingItem.isFreeDelivery() 
                                : freeDeliveryInput.equalsIgnoreCase("yes"); 
 
                        MenuItem updated = new MenuItem(mid, name, price, active, 
date, category, freeDelivery); 
                        menuItemService.modifyMenuItem(updated); 
                        System.out.println("Item updated successfully."); 
                        break; 
 
                    case 4: 
                        System.out.print("Name: "); 
                        String newName = sc.nextLine(); 
 
                        float newPrice = -1; 
                        while (newPrice <= 0) { 
                            System.out.print("Price: "); 
                            String priceInput = sc.nextLine(); 
                            try { 
                                newPrice = Float.parseFloat(priceInput); 
                                if (newPrice <= 0) System.out.println("Price must 
be greater than 0."); 
                            } catch (NumberFormatException e) { 
                                System.out.println("Invalid price. Enter a valid 
number."); 
                            } 
                        } 
 
                        System.out.print("Is Active (yes/no): "); 
                        boolean newActive = sc.nextLine().equalsIgnoreCase("yes"); 
 
                        System.out.print("Date of Launch (dd/MM/yyyy): "); 
                        String newDateStr = sc.nextLine(); 
 
                        System.out.print("Category: "); 
                        String newCategory = sc.nextLine(); 
 
                        System.out.print("Free Delivery (yes/no): "); 
                        boolean newFreeDelivery = 
sc.nextLine().equalsIgnoreCase("yes"); 
 
                        MenuItem newItem = new MenuItem( 
                                0, 
                                newName, 
                                newPrice, 
                                newActive, 
                                DateUtil.convertToDate(newDateStr), 
                                newCategory, 
                                newFreeDelivery 
                        ); 
 
                        long newId = menuItemService.addMenuItem(newItem); 
 
                        if (newId != -1) { 
                            System.out.println("New item added successfully with 
ID: " + newId); 
                        } else { 
                            System.out.println("Failed to add new item."); 
                        } 
 
                        break; 
 
                    case 5: 
                        System.out.println(" Exiting Admin menu."); 
                        sc.close(); 
                        System.exit(0); 
                        break; 
 
                    default: 
                        System.out.println(" Invalid choice."); 
                } 
            } 
 
        } else if (roleChoice == 2) { 
            System.out.println("\n=== Customer Login/Register ==="); 
 
            long userId = -1; 
            while (true) { 
                System.out.print("Enter your User ID (numeric): "); 
                String idInput = sc.nextLine(); 
                try { 
                    userId = Long.parseLong(idInput); 
                    break; 
                } catch (NumberFormatException e) { 
                    System.out.println("Invalid input. Please enter a numeric user 
ID."); 
                } 
            } 
 
            String username = ""; 
            while (true) { 
                System.out.print("Enter your Name: "); 
                username = sc.nextLine(); 
                if (!username.trim().isEmpty()) { 
                    break; 
                } else { 
                    System.out.println("Name cannot be empty. Please enter a valid 
name."); 
                } 
            } 
 
            try (Connection conn = ConnectionHandler.getConnection()) { 
                String idCheckQuery = "SELECT us_name FROM user WHERE us_id = ?"; 
                PreparedStatement idCheckStmt = 
conn.prepareStatement(idCheckQuery); 
                idCheckStmt.setLong(1, userId); 
                ResultSet idCheckRs = idCheckStmt.executeQuery();
               PreparedStatement idCheckStmt = 
conn.prepareStatement(idCheckQuery); 
                idCheckStmt.setLong(1, userId); 
                ResultSet idCheckRs = idCheckStmt.executeQuery(); 
 
                if (idCheckRs.next()) { 
                    String existingName = idCheckRs.getString("us_name"); 
                    if (existingName.equalsIgnoreCase(username)) { 
                        System.out.println("Welcome back, " + username + " (User 
ID: " + userId + ")"); 
                    } else { 
                        System.out.println("User ID already exists with a 
different name (" + existingName + ")."); 
                        System.out.println("Login aborted. Please try with correct 
credentials."); 
                        return; 
                    } 
                } else { 
                    String insertQuery = "INSERT INTO user (us_id, us_name) VALUES 
(?, ?)"; 
                    PreparedStatement insertPs = 
conn.prepareStatement(insertQuery); 
                    insertPs.setLong(1, userId); 
                    insertPs.setString(2, username); 
 
                    int rows = insertPs.executeUpdate(); 
                    if (rows > 0) { 
                        System.out.println("New user registered. Welcome, " + 
username + " (User ID: " + userId + ")"); 
                    } else { 
                        System.out.println("Failed to register user."); 
                        return; 
                    } 
                } 
 
            } catch (Exception e) { 
                System.out.println("Error during login/registration."); 
                e.printStackTrace(); 
                return; 
            } 
 
            while (true) { 
                System.out.println("\n Customer Menu "); 
                System.out.println("1. View Menu Items"); 
                System.out.println("2. Add Item to Cart"); 
                System.out.println("3. View Cart"); 
                System.out.println("4. Remove Item from Cart"); 
                System.out.println("5. Exit"); 
                System.out.print("Enter your choice: "); 
                int choice = sc.nextInt(); 
                sc.nextLine(); 
 
                switch (choice) { 
                    case 1: 
                        List<MenuItem> items = 
menuItemService.getMenuItemListCustomer(); 
                        for (MenuItem mi : items) { 
                            System.out.println(mi); 
                        } 
                        break; 
 
                    case 2: 
                        System.out.print("Enter Item ID to Add to Cart: "); 
                        long addId = sc.nextLong(); 
                        sc.nextLine(); 
                        MenuItem itemToAdd = menuItemService.getMenuItem(addId); 
                        if (itemToAdd == null) { 
                            System.out.println("Item ID does not exist. Please try 
again."); 
                        } else { 
                            cartService.addCartItem(userId, addId); 
                            System.out.println("Item added to cart: " + 
itemToAdd.getName()); 
                        } 
                        break; 
 
                    case 3: 
                        try { 
                            List<MenuItem> cartItems = 
cartService.getAllCartItems(userId); 
                            float total = 0; 
                            for (MenuItem ci : cartItems) { 
                                System.out.println(ci); 
                                total += ci.getPrice(); 
                            } 
                            System.out.println("Total: ₹" + total); 
                        } catch (CartEmptyException e) { 
                            System.out.println(e.getMessage()); 
                        } 
                        break; 
 
                    case 4: 
                        System.out.print("Enter Item ID to Remove: "); 
                        long remId = sc.nextLong(); 
                        sc.nextLine(); 
                        cartService.removeCartItem(userId, remId); 
                        break; 
 
                    case 5: 
                        System.out.println(" Exiting Customer menu."); 
                        sc.close(); 
                        System.exit(0); 
                        break; 
 
                    default: 
                        System.out.println(" Invalid choice"); 
                } 
            } 
 
        } else { 
            System.out.println(" Invalid role selection. Exiting.");
        }
    }
}
