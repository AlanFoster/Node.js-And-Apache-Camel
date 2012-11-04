package me.alanfoster.shoppingcart.webservice.tests;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import me.alanfoster.shoppingcart.webservice.ShoppingCartPortTypeImpl;
import me.alanfoster.shoppingcart.webservice.util.CustomerAssert;
import me.alanfoster.shoppingcart.webservice.util.CustomerFactory;
import me.alanfoster.shoppingcart.webservice.util.ProductFactory;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.AddProductToCustomerAccountRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.AddProductToCustomerAccountResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CartItemType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCart;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartType;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;

public class Steps {
	
	private ShoppingCartPortTypeImpl shoppingCartPortTypeImpl;
	
	private AddProductToCustomerAccountResponse response;
	
	public ShoppingCartPortTypeImpl getShoppingCartPortType() {
		if(shoppingCartPortTypeImpl == null) {
			shoppingCartPortTypeImpl = new ShoppingCartPortTypeImpl();
		}
		return shoppingCartPortTypeImpl;
	}
	
	@Given("^the Api has the following list of customers with empty shopping carts$")
	public void the_Api_has_the_following_list_of_customers_with_empty_shopping_carts(List<CustomerType> customers) throws Throwable {
		for(CustomerType customer : customers) {
			customer.setShoppingCart(new ShoppingCartType());
		}
		getShoppingCartPortType().setCustomers(customers);
	}

	@Given("^the Api has the following products$")
	public void the_Api_has_the_following_products(List<ProductType> products) throws Throwable {
		getShoppingCartPortType().setProducts(products);
	}

	@When("^I call the addProductToCustomerAccount operation with the following information$")
	public void I_call_the_addProductToCustomerAccount_operation_with_the_following_information(List<AddProductToCustomerAccountRequest> requests) throws Throwable {
		response = getShoppingCartPortType().addProductToCustomerAccount(requests.get(0));
	}
	

	@Then("^there will be no errors given in the response$")
	public void there_will_be_no_errors_given_in_the_response() throws Throwable {
	   assertFalse("There should be no error in the response", response.isError());
	   assertNull("There should be no error description in the response", response.getErrorReason());
	}

	@Then("^the returned customer shall have the following core information$")
	public void the_returned_customer_shall_have_the_following_core_information(DataTable customers) throws Throwable {
		CustomerType actualCustomer = response.getCustomer();
		
		Map<String, String> data = customers.asMaps().get(0);
		String expectedCustomerId = data.get("CustomerId");
		String expectedEmail = data.get("Email");
		String expectedPassword = data.get("Password");
		
		assertNotNull("There should have been a customer object returned", actualCustomer);
    	assertEquals("Both customers must have the same customer id", expectedCustomerId, actualCustomer.getCustomerId());
    	assertEquals("Both customers must have the same email", expectedEmail, actualCustomer.getEmail());
    	assertEquals("Both customers must have the same password", expectedPassword, actualCustomer.getPassword());
	}

	@Then("^the returned customer shall have the following shopping cart$")
	public void the_returned_customer_shall_have_the_following_shopping_cart(DataTable datatable) throws Throwable {
		// Construct the expected shopping cart
		ShoppingCartType expectedShoppingCart = new ShoppingCartType();
		List<Map<String, String>> maps = datatable.asMaps();
		for(Map<String, String> map : maps) {
			CartItemType cartItem = new CartItemType();
			cartItem.setQuantity(new BigInteger(map.get("Quantity")));
			cartItem.setProduct(
					ProductFactory.getNewProduct(
							map.get("ProductId"), map.get("Name"), map.get("Description"), Float.parseFloat(map.get("Price")
					))
			);
			expectedShoppingCart.getCartItem().add(cartItem);
		}
		
		ShoppingCartType actualShoppingCart = response.getCustomer().getShoppingCart();
		assertNotNull("There should have been a shopping cart object returned", actualShoppingCart);
		CustomerAssert.assertEqual(expectedShoppingCart, actualShoppingCart);
	}
}
