package me.alanfoster.shoppingcart.webservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.Product;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartPortType;

public class ShoppingCartPortTypeImpl implements ShoppingCartPortType {

    private static final Logger logger = Logger.getLogger(ShoppingCartPortTypeImpl.class);

    /**
     * ProductId mapping to Product
     */
    private Map<String, Product> products;
    
    public ShoppingCartPortTypeImpl() {
    	products = new HashMap<String, Product>();
    }
    
    protected List<Product> getProducts() {
    	return new LinkedList<Product>(products.values());
    }
    
    public void setProducts(List<Product> products) {
    	for(Product product : products) {
    		this.products.put(product.getProductId(), product);
    	}
    }
    
    /* (non-Javadoc)
     * @see me.alanfoster.ShoppingCartPortType#getProduct(me.alanfoster.GetProductRequest  body )*
     */
	@Override
	public Product getProduct(GetProductRequest body) {
		logger.info("Executing operation getProduct");
        
        return products.get(body.getProductId());
    }
}
