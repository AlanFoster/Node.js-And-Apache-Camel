package me.alanfoster.shoppingcart.webservice;

import java.util.Arrays;
import java.util.List;

import me.alanfoster.shoppingcart.webservice.util.CustomerFactory;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static me.alanfoster.shoppingcart.webservice.util.CustomerAssert.*;

public class CustomerTests {
	Logger logger = LoggerFactory.getLogger(CustomerTests.class);

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	public ShoppingCartPortTypeImpl getShoppingCartPortType() {
		return new ShoppingCartPortTypeImpl();
	}
	
    @Test
    public void testGetKnownCustomerWithValidPassword() throws Exception {
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestEmail = "alan@foo.com";
    	String requestPassword = "rawTextForNow";
    	
    	CustomerType expectedCustomer = CustomerFactory.getNewCustomer(customerEmail, customerPassword);
    	List<CustomerType> customers = Arrays.asList(expectedCustomer);
     	
    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertFalse("Customer should have been found", response.isError());
    	CustomerType actualCustomer = response.getCustomer();
    	
    	assertEqual(expectedCustomer, actualCustomer);
    }
    
    @Test
    public void testGetKnownCustomerWithInvalidPassword() throws Exception {
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestEmail = "alan@foo.com";
    	String requestPassword = "WrongPassword";
    	
    	CustomerType expectedCustomer = CustomerFactory.getNewCustomer(customerEmail, customerPassword);
    	List<CustomerType> customers = Arrays.asList(expectedCustomer);
     	
    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertTrue("Customer should not have been found", response.isError());
    	assertEquals("Valid description should be returned", "No Matching Customer Found", response.getErrorReason());
    	CustomerType actualCustomer = response.getCustomer();
    	assertNull("No customer should have been returned", actualCustomer);
    }
    
    @Ignore
    @Test
    public void testGetUnknownCustomer() throws Exception {
    	// TODO
    }
    
    @Test
    public void testGetKnownCustomerShoppingCartWithValidPassword() throws Exception {
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestEmail = "alan@foo.com";
    	String requestPassword = "rawTextForNow";
    	
    	CustomerType expectedCustomer = CustomerFactory.getNewCustomer(customerEmail, customerPassword);
    	List<CustomerType> customers = Arrays.asList(expectedCustomer);

    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertFalse("Customer should have been found", response.isError());
    	CustomerType actualCustomer = response.getCustomer();
    	
    	assertEqual(expectedCustomer, actualCustomer);
    }


}
