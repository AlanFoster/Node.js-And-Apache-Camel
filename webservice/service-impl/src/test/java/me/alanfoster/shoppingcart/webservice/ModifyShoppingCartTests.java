package me.alanfoster.shoppingcart.webservice;

import static me.alanfoster.shoppingcart.webservice.util.CustomerAssert.assertEqual;
import static org.junit.Assert.assertFalse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import me.alanfoster.shoppingcart.webservice.util.CustomerFactory;
import me.alanfoster.shoppingcart.webservice.util.ProductFactory;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.AddProductToCustomerAccountRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.AddProductToCustomerAccountResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CartItemType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModifyShoppingCartTests {
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
    public void testAddProductWithNoExistingProductsInShoppingCart() throws Exception {
    	String customerId = "1";
    	String customerEmail = "alan@foo.com";
    	String customerPassword = "rawTextForNow";
    	
    	String requestId = customerId;
    	String requestProductId = "2";

		ProductType expectedProduct = ProductFactory.getNewProduct(requestProductId, "Duracell AA Battery", "Lasts longer", 4f);
    	List<ProductType> products = Arrays.asList(expectedProduct);
    	
    	
    	CustomerType newCustomer = CustomerFactory.getNewCustomer(customerId, customerEmail, customerPassword);
    	
    	CustomerType expectedCustomer = CustomerFactory.getNewCustomer(customerId, customerEmail, customerPassword);
    	CartItemType cartItem = new CartItemType();
    	cartItem.setQuantity(BigInteger.ONE);
    	cartItem.setProduct(expectedProduct);
    	expectedCustomer.getShoppingCart().getCartItem().add(cartItem);
    	
    	List<CustomerType> customers = Arrays.asList(newCustomer);
     	
    	AddProductToCustomerAccountRequest request = new AddProductToCustomerAccountRequest();
    	request.setCustomerId(customerId);
    	request.setProductId(requestProductId);
    	request.setQuantity(BigInteger.ONE);

    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setCustomers(customers);
    	shoppingCart.setProducts(products);
    	
    	AddProductToCustomerAccountResponse response = shoppingCart.addProductToCustomerAccount(request);
    	assertFalse("Customer should have been found", response.isError());
    	CustomerType actualCustomer = response.getCustomer();
    	
    	assertEqual(expectedCustomer, actualCustomer);
    }
}
