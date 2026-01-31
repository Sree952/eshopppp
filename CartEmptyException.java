package com.wipro.eshop.exception; 
 
public class CartEmptyException extends Exception { 
     
 private static final long serialVersionUID = 1L; 
 
 public CartEmptyException(String message) { 
        super(message); 
 }
}
