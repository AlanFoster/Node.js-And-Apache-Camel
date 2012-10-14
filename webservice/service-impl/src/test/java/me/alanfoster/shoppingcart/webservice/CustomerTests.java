package me.alanfoster.shoppingcart.webservice;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

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
    	
    	CustomerType expectedCustomer = new CustomerType();
    	expectedCustomer.setEmail(customerEmail);
    	expectedCustomer.setPassword(customerPassword);
    	List<CustomerType> customers = Arrays.asList(expectedCustomer);
     	
    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertTrue("Customer should have been found", response.isSuccess());
    	CustomerType actualCustomer = response.getCustomer();
    	
    	assertEqualCustomer(expectedCustomer, actualCustomer);
    }
    
    @Test
    public void testGetKnownCustomerWithInvalidPassword() throws Exception {
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestEmail = "alan@foo.com";
    	String requestPassword = "WrongPassword";
    	
    	CustomerType expectedCustomer = new CustomerType();
    	expectedCustomer.setEmail(customerEmail);
    	expectedCustomer.setPassword(customerPassword);
    	List<CustomerType> customers = Arrays.asList(expectedCustomer);
     	
    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertFalse("Customer should not have been found", response.isSuccess());
    	CustomerType actualCustomer = response.getCustomer();
    	assertNull("No customer should have been returned", actualCustomer);
    }
    
    @Ignore
    @Test
    public void testGetUnknownCustomer() throws Exception {
    	// TODO
    }
    
    @Ignore
    @Test
    public void testGetKnownCustomerShoppingCartWithValidPassword() throws Exception {
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestEmail = "alan@foo.com";
    	String requestPassword = "rawTextForNow";
    	
    	CustomerType expectedCustomer = new CustomerType();
    	expectedCustomer.setEmail(customerEmail);
    	expectedCustomer.setPassword(customerPassword);
     	
    	GetCustomerRequest request = new GetCustomerRequest();
    	request.setEmail(requestEmail);
    	request.setPassword(requestPassword);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	GetCustomerResponse response = shoppingCart.getCustomer(request);
    	assertFalse("Customer should not have been found", response.isSuccess());
    	CustomerType actualCustomer = response.getCustomer();
    	
    	assertEqualCustomer(expectedCustomer, actualCustomer);
    }
    
    
    private static ProductType getNewProduct(String id, String name, String description, float price) {
    	ProductType product = new ProductType();
    	product.setProductId(id);
    	product.setName(name);
    	product.setDescription(description);
    	product.setPrice(price);
    	return product;
    }
    
    private static void assertEqualProduct(ProductType expectedProduct, ProductType actualProduct) {
    	assertEquals("Product id should be the same", expectedProduct.getProductId(), actualProduct.getProductId());
    	assertEquals("Product name should be the same", expectedProduct.getName(), actualProduct.getName());
    	assertEquals("Product description should be the same", expectedProduct.getDescription(), actualProduct.getDescription());
    	assertEquals("Product price should be the same", expectedProduct.getPrice(), actualProduct.getPrice(), 1e-15);
    }
    
    private static void assertEqualProduct(List<ProductType> expectedProducts, List<ProductType> actualProducts) {
    	assertEquals("Both product lists should be the same size", expectedProducts.size(), actualProducts.size());
    	Iterator<ProductType> expectedProductsIterator = expectedProducts.iterator();
    	Iterator<ProductType> actualProductsIterator = expectedProducts.iterator();
    	while(expectedProductsIterator.hasNext() && actualProductsIterator.hasNext()) {
    		assertEqualProduct(expectedProductsIterator.next(), actualProductsIterator.next());
    	}
    }
    
    private static void assertEqualCustomer(CustomerType expectedCustomer, CustomerType actualCustomer) {
    	assertEquals("Both customers must have the same email", expectedCustomer.getEmail(), actualCustomer.getEmail());
    	assertEquals("Both customers must have the same password", expectedCustomer.getPassword(), actualCustomer.getPassword());
    }
}
