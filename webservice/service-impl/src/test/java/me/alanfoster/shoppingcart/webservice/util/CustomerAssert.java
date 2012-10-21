package me.alanfoster.shoppingcart.webservice.util;

import static org.junit.Assert.assertEquals;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;

public class CustomerAssert {

    public static void assertEqual(CustomerType expectedCustomer, CustomerType actualCustomer) {
    	assertEquals("Both customers must have the same email", expectedCustomer.getEmail(), actualCustomer.getEmail());
    	assertEquals("Both customers must have the same password", expectedCustomer.getPassword(), actualCustomer.getPassword());
    }
    
}
