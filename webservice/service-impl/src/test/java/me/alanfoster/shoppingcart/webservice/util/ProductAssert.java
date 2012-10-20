package me.alanfoster.shoppingcart.webservice.util;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

public class ProductAssert {
    public static void assertEqual(ProductType expectedProduct, ProductType actualProduct) {
    	assertEquals("Product id should be the same", expectedProduct.getProductId(), actualProduct.getProductId());
    	assertEquals("Product name should be the same", expectedProduct.getName(), actualProduct.getName());
    	assertEquals("Product description should be the same", expectedProduct.getDescription(), actualProduct.getDescription());
    	assertEquals("Product price should be the same", expectedProduct.getPrice(), actualProduct.getPrice(), 1e-15);
    }
    
    public static void assertEqual(List<ProductType> expectedProducts, List<ProductType> actualProducts) {
    	assertEquals("Both product lists should be the same size", expectedProducts.size(), actualProducts.size());
    	Iterator<ProductType> expectedProductsIterator = expectedProducts.iterator();
    	Iterator<ProductType> actualProductsIterator = expectedProducts.iterator();
    	while(expectedProductsIterator.hasNext() && actualProductsIterator.hasNext()) {
    		assertEqual(expectedProductsIterator.next(), actualProductsIterator.next());
    	}
    }
    
}
