package me.alanfoster.shoppingcart.webservice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class WsdlTest {
	Logger logger = LoggerFactory.getLogger(WsdlTest.class);
	
    @Test
    public void testFine() throws Exception {
        logger.info("Pass");
        assertTrue(true);
    }
   
    @Test(expected=AssertionError.class)
    public void testException() throws Exception {
        logger.info("Pass");
        assertTrue(false);
    }

}
