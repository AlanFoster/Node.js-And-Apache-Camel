package me.alanfoster.shoppingcart.webservice;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ProductTests {
	Logger logger = LoggerFactory.getLogger(ProductTests.class);

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
    public void testGetKnownProduct() throws Exception {
    	ProductType expectedProduct = getNewProduct("1", "Duracell AA Battery", "Lasts longer", 4f);
    	List<ProductType> products = Arrays.asList(expectedProduct);
    	
    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setProducts(Arrays.asList(expectedProduct));
    	
    	GetProductRequest request = new GetProductRequest();
    	request.setProductId(expectedProduct.getProductId());
    	
    	GetProductResponse response = shoppingCart.getProduct(request);
    	ProductType actualProduct = response.getProduct();
    	
    	assertEqualProduct(expectedProduct, actualProduct);
    }
    
    @Test
    public void testGetAllProducts() throws Exception {
	    List<ProductType> productsExpected = Arrays.asList(
			getNewProduct("1", "Duracell AA Battery", "Lasts longer", 4f),
			getNewProduct("2", "Milk", "Only the freshest", 0.89f),
			getNewProduct("3", "Eggs", "Free Range", 1.19f),
			getNewProduct("4", "Bread", "3 day life", 1.20f)
		);
	
    	ShoppingCartPortTypeImpl shoppingCart = getShoppingCartPortType();
    	shoppingCart.setProducts(productsExpected);
    	
    	GetAllProductsRequest request = new GetAllProductsRequest();
    	
    	GetAllProductsResponse response = shoppingCart.getAllProducts(request);
    	List<ProductType> productsActual = response.getProducts().getProduct();
    	
    	assertEqualProduct(productsExpected, productsActual);
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

}
