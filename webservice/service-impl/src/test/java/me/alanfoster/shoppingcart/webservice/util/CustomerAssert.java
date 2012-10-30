package me.alanfoster.shoppingcart.webservice.util;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CartItemType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartType;

public class CustomerAssert {

    public static void assertEqual(CustomerType expectedCustomer, CustomerType actualCustomer) {
    	assertEquals("Both customers must have the same email", expectedCustomer.getEmail(), actualCustomer.getEmail());
    	assertEquals("Both customers must have the same password", expectedCustomer.getPassword(), actualCustomer.getPassword());
    	assertEqual(expectedCustomer.getShoppingCart(), actualCustomer.getShoppingCart());
    }
    
    public static void assertEqual(ShoppingCartType  expected, ShoppingCartType actual) {
    	assertEquals("Both product lists should be the same size", expected.getCartItem().size(), actual.getCartItem().size());
    	Iterator<CartItemType> expectedCartItemIterator = expected.getCartItem().iterator();
    	Iterator<CartItemType> actualCartItemIterator = actual.getCartItem().iterator();
    	while(expectedCartItemIterator.hasNext() && actualCartItemIterator.hasNext()) {
    		assertEqual(expectedCartItemIterator.next(), actualCartItemIterator.next());
    	}
    }
    
    public static void assertEqual(CartItemType expected, CartItemType actual) {
    	assertEquals("Both quantities should be the same", expected.getQuantity(), actual.getQuantity());
    	ProductAssert.assertEqual(expected.getProduct(), actual.getProduct());
    }
    
    
}
