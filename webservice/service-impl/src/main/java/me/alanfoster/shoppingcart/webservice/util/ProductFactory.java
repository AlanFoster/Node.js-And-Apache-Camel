package me.alanfoster.shoppingcart.webservice.util;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;

public class ProductFactory {
    public static ProductType getNewProduct(String id, String name, String description, float price) {
    	ProductType product = new ProductType();
    	product.setProductId(id);
    	product.setName(name);
    	product.setDescription(description);
    	product.setPrice(price);
    	return product;
    }
    
    public static ProductType getNewProduct(ProductType product) {
    	return getNewProduct(product.getProductId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
