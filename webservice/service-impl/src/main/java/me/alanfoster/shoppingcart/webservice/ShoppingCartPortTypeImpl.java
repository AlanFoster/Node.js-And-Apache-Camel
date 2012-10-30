package me.alanfoster.shoppingcart.webservice;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import me.alanfoster.shoppingcart.webservice.util.CustomerFactory;
import me.alanfoster.shoppingcart.webservice.util.ProductFactory;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CartItemType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.CustomerType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetAllProductsResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetCustomerResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductRequest;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.GetProductResponse;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ProductsType;
import me.alanfoster.tests.shoppingcart.wsdl.proxyclasses.ShoppingCartPortType;

public class ShoppingCartPortTypeImpl implements ShoppingCartPortType {

    private static final Logger logger = Logger.getLogger(ShoppingCartPortTypeImpl.class);

    /**
     * ProductId mapping to Product
     */
    private Map<String, ProductType> products;
    private List<CustomerType> customers;
    
    public ShoppingCartPortTypeImpl() {

    }
    
    public void init() {
    	logger.info("Initialised");
    	
    	setProducts(getDefaultProducts());
    	setCustomers(getDefaultCustomers());
    }
    
    private List<ProductType> getDefaultProducts() {
	    List<ProductType> defaultProducts = Arrays.asList(
			ProductFactory.getNewProduct("1", "Duracell AA Battery", "Lasts longer", 4f),
			ProductFactory.getNewProduct("2", "Milk", "Only the freshest", 0.89f),
			ProductFactory.getNewProduct("3", "Eggs", "Free Range", 1.19f),
			ProductFactory.getNewProduct("4", "Bread", "3 day life", 1.20f)
		);
	    return defaultProducts;
    }
    
    private List<CustomerType> getDefaultCustomers() {
    	List<CustomerType> customers = new LinkedList<CustomerType>();
    	CustomerType customer = CustomerFactory.getNewCustomer("foo", "bar");
    	CartItemType cartItem = new CartItemType();
    	cartItem.setQuantity(BigInteger.ONE);
    	cartItem.setProduct(ProductFactory.getNewProduct("1", "Cheese", "It's cheese", 1.99f));
    	customer.getShoppingCart().getCartItem().add(cartItem);
    	customers.add(customer);
    	
    	return customers;
    }
    
    protected List<ProductType> getProducts() {
    	return new LinkedList<ProductType>(products.values());
    }
    
    public void setProducts(List<ProductType> products) {
    	this.products = new LinkedHashMap<String, ProductType>();
    	for(ProductType product : products) {
    		this.products.put(product.getProductId(), product);
    	}
    }
    
    protected List<CustomerType> getCustomers() {
    	return new LinkedList<CustomerType>(customers);
    }
    
    public void setCustomers(List<CustomerType> customers) {
    	this.customers = customers;
    }
    
	@Override
	public GetProductResponse getProduct(GetProductRequest body) {
		logger.info("Executing operation getProduct");
		ProductType product = products.get(body.getProductId());
       
		GetProductResponse response = new GetProductResponse();
        response.setProduct(product);
        if(product == null) {
			response.setError(true);
			response.setErrorReason("There was no matching product for this product id");
        }
        return response;
    }

	@Override
	public GetAllProductsResponse getAllProducts(GetAllProductsRequest body) {
		GetAllProductsResponse response = new GetAllProductsResponse();
		ProductsType products = new ProductsType();
		products.getProduct().addAll(getProducts());
		response.setProducts(products);
		return response;
	}

	@Override
	public GetCustomerResponse getCustomer(GetCustomerRequest body) {		
		String email = body.getEmail();
		String password = body.getPassword();
		
		CustomerType customer = getCustomer(email, password);
		
		GetCustomerResponse response = new GetCustomerResponse();
		response.setCustomer(customer);
		if(customer == null) {
			response.setError(true);
			response.setErrorReason("No Matching Customer Found");
		}
		
		return response;
	}
	
	private CustomerType getCustomer(String email, String password) {
		for(CustomerType customer : customers) {
			if(customer.getPassword().equals(password) && customer.getEmail().equals(email)) {
				return customer;
			}
		}
		return null;
	}
}