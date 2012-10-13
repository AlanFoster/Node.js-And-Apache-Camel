package me.alanfoster.shoppingcart.webservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartPortType;

public class ShoppingCartPortTypeImpl implements ShoppingCartPortType {

    private static final Logger logger = Logger.getLogger(ShoppingCartPortTypeImpl.class);

    /**
     * ProductId mapping to Product
     */
    private Map<String, ProductType> products;
    
    public ShoppingCartPortTypeImpl() {
    	products = new HashMap<String, ProductType>();
    }
    
    protected List<ProductType> getProducts() {
    	return new LinkedList<ProductType>(products.values());
    }
    
    public void setProducts(List<ProductType> products) {
    	for(ProductType product : products) {
    		this.products.put(product.getProductId(), product);
    	}
    }
    
    /* (non-Javadoc)
     * @see me.alanfoster.ShoppingCartPortType#getProduct(me.alanfoster.GetProductRequest  body )*
     */
	@Override
	public GetProductResponse getProduct(GetProductRequest body) {
		logger.info("Executing operation getProduct");
		ProductType product = products.get(body.getProductId());
       
		GetProductResponse response = new GetProductResponse();
        response.setProduct(product);
        return response;
    }

	@Override
	public GetAllProductsResponse getAllProducts(Object arg0) {
		throw new UnsupportedOperationException();
	}
}
