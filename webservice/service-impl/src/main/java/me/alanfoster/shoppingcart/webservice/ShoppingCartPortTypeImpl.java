package me.alanfoster.shoppingcart.webservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
    	products = new HashMap<String, ProductType>();
    }
    
    public void init() {
    	logger.info("Initialised");
    	setProducts(new LinkedList<ProductType>());
    	setCustomers(new LinkedList<CustomerType>());
    }
    
    protected List<ProductType> getProducts() {
    	return new LinkedList<ProductType>(products.values());
    }
    
    public void setProducts(List<ProductType> products) {
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
		
		CustomerType customer = foo(); //getCustomer(email, password);
		
		GetCustomerResponse response = new GetCustomerResponse();
		response.setCustomer(customer);
		response.setSuccess(customer != null);
		
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
	
	private CustomerType foo() {
    	CustomerType expectedCustomer = new CustomerType();
    	expectedCustomer.setEmail("a");
    	expectedCustomer.setPassword("b");
    	return expectedCustomer;
	}
}