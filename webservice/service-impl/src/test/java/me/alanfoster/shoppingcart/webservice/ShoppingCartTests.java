package me.alanfoster.shoppingcart.webservice;

import java.util.Arrays;
import java.util.List;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ShoppingCartTests {
	Logger logger = LoggerFactory.getLogger(ShoppingCartTests.class);
		
	private List<ProductType> products;
	
	@Before
	public void setUp() {
    	products = Arrays.asList(
			getNewProduct("1", 1f),
			getNewProduct("2", 1.50f),
			getNewProduct("3", 5f),
			getNewProduct("4", 0.5f)
		);
	}
	
	@After
	public void tearDown() {
		
	}
	
	public ShoppingCartPortTypeImpl getShoppingCartPortType() {
		return new ShoppingCartPortTypeImpl();
	}
	
    @Test
    public void testGetKnownProduct() throws Exception {
    	ProductType expectedProduct = getNewProduct("1", 1f);
    	
    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setProducts(products);
    	
    	GetProductRequest request = new GetProductRequest();
    	request.setProductId("1");
    	
    	GetProductResponse response = shoppingCart.getProduct(request);
    	ProductType actualProduct = response.getProduct();
    	
    	assertEquals("Product id should be the same", expectedProduct.getProductId(), actualProduct.getProductId());
    	assertEquals("Price should be the same", expectedProduct.getPrice(), actualProduct.getPrice(), 1e-15);
    }
   
    private static ProductType getNewProduct(String id, float price) {
    	ProductType product = new ProductType();
    	product.setProductId(id);
    	product.setPrice(price);
    	return product;
    }
}
