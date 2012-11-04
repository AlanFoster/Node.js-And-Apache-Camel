package me.alanfoster.shoppingcart.webservice.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static junit.framework.Assert.*;

public class Steps {
	@Given("^this works$")
	public void given_this_works() throws Throwable {
		assertTrue(true);
	}

	@Then("^i shall be happy$")
	public void then_this_works() throws Throwable {
		assertTrue(true);
	}
}
