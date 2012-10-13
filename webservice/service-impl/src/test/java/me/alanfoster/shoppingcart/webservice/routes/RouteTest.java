package me.alanfoster.shoppingcart.webservice.routes;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

import org.junit.Ignore;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/ShoppingCartWebservice.xml";
    }

    @Ignore
    @Test
    public void testRoute() throws Exception {
   
    	/*template().sendBody("cxf:bean:shoppingcart-webservice", "test");

        getMockEndpoint("mock:result").expectedMinimumMessageCount(1);

        // assert expectations
        assertMockEndpointsSatisfied();*/
    	
    	
    }

}
