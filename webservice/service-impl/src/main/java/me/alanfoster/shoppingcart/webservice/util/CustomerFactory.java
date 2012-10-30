package me.alanfoster.shoppingcart.webservice.util;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartType;

public class CustomerFactory {
	public static CustomerType getNewCustomer(String email, String password) {
		CustomerType customer = new CustomerType();
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setShoppingCart(getNewShoppingCart());
		return customer;
	}
	
	public static ShoppingCartType getNewShoppingCart() {
		ShoppingCartType shoppingCart = new ShoppingCartType();
		return shoppingCart;
	}
}
