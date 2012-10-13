package me.alanfoster.shoppingcart.webservice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.Product;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartPortType;

public class ShoppingCartPortTypeImpl implements ShoppingCartPortType {

    private static final Logger LOG = Logger.getLogger(ShoppingCartPortTypeImpl.class.getName());

    /**
     * ProductId mapping to Product
     */
    private static Map<String, Product> products;
    
    static {
    	products = new HashMap<String, Product>();
        
    }
    
    /* (non-Javadoc)
     * @see me.alanfoster.ShoppingCartPortType#getProduct(me.alanfoster.GetProductRequest  body )*
     */
	@Override
	public Product getProduct(GetProductRequest body) {
        LOG.info("Executing operation getProduct");
        System.out.println(body);
        
        String productId = body.getProductId();
        
        return null;
    }
}
