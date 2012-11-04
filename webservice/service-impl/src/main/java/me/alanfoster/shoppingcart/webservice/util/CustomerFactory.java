package me.alanfoster.shoppingcart.webservice.util;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CartItemType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartType;

public class CustomerFactory {
	public static CustomerType getNewCustomer(String customerId, String email, String password) {
		CustomerType customer = new CustomerType();
		customer.setCustomerId(customerId);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setShoppingCart(getNewShoppingCart());
		return customer;
	}
	
	public static ShoppingCartType getNewShoppingCart() {
		ShoppingCartType shoppingCart = new ShoppingCartType();
		return shoppingCart;
	}
	
	public static CustomerType getNewCustomer(CustomerType customer) {
		CustomerType newCustomer = getNewCustomer(customer.getCustomerId(), customer.getEmail(), customer.getPassword());
		for(CartItemType cartItem : customer.getShoppingCart().getCartItem()) {
			CartItemType newCartItem = new CartItemType();
			newCartItem.setQuantity(cartItem.getQuantity());
			newCartItem.setProduct(ProductFactory.getNewProduct(cartItem.getProduct()));
			newCustomer.getShoppingCart().getCartItem().add(newCartItem);
		}
		
		return newCustomer;
	}
}
